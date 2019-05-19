/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *
 * @author juanmurillo
 */
@WebServlet(urlPatterns = {"/LabIntegration"})
public class LabIntegration extends HttpServlet {

    private String estudiantesJsonString;
    ArrayList<Estudiante> estudiantes;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

        //ServletOutputStream out = resp.getOutputStream();
        if (estudiantes == null) {
            estudiantes = new ArrayList<>();
        }
        if (estudiantes.size() < 1) {
            estudiantes.clear();
            estudiantes.add(new Estudiante("Juan", 39));
            estudiantes.add(new Estudiante("Pedro", 34));
            estudiantes.add(new Estudiante("Anibal", 33));
            estudiantes.add(new Estudiante("Juanito", 32));
        }
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        // Adding new elements to the ArrayList
        String opcion = (String) req.getParameter("opc");
        switch (Integer.parseInt(opcion)) {
            //Listar estudiantes
            case 1:
                estudiantesJsonString = gson.toJson(estudiantes);
                try {
                    out.println(estudiantesJsonString);
                } finally {
                    out.close();
                }
                break;
            //Agregar estudiante
            case 2:
                try {
                    String nombre = req.getParameter("nombre");
                    int edad = Integer.parseInt(req.getParameter("edad"));
                    if (!nombre.equalsIgnoreCase("") && edad >= 0) {
                        estudiantes.add(new Estudiante(nombre, edad));
                        out.println("Estudiante agregado con exito");
                    } else {
                        out.println("Estudiante no agregado, datos erroneos");
                    }
                } finally {
                    out.close();
                }
                break;
            //Elimina el ultimo estudiante en la lista ya que no tienen identificador unico
            case 3:
                try {
                    int tamano = estudiantes.size();
                    estudiantes.remove(tamano-1);
                    if (tamano > estudiantes.size()) {
                        out.println("ultimo estudiante eliminado");
                    } else {
                        out.println("No se realizo la eliminacion");
                    }
                } finally {
                    out.close();
                }
                break;
            case 4:
                try {
                    int tamano = estudiantes.size();
                    Estudiante estudiante = estudiantes.get(tamano -1);
                    String nombre = req.getParameter("nombre");
                    int edad = Integer.parseInt(req.getParameter("edad"));
                    if (!nombre.equalsIgnoreCase("") && edad >= 0) {
                        estudiante.setEdad(edad);
                        estudiante.setNombre(nombre);
                        estudiantes.set(tamano-1, estudiante);
                        out.println("Estudiante modificado con exito");
                    } else {
                        out.println("Estudiante no modificado, datos erroneos");
                    }
                } finally {
                    out.close();
                }
                break;
            case 5:
                
                estudiantesJsonString = gson.toJson(estudiantes.get(estudiantes.size() - 1));
                try {
                    out.println(estudiantesJsonString);
                } finally {
                    out.close();
                }
                break;
        }

    }

    private void response(HttpServletResponse resp, String msg)
            throws IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
