package com.example.mobilecontrol.LogicaNegocio;
import java.io.Serializable;
public class Estudiante implements Serializable {

    private String id;
    private int edad;
    private String nombre;

    public Estudiante(){
        id = new String();
        nombre = new String();
        edad = 0;
    }
    public Estudiante(String id, String nombre, int edad){
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }
    /**
     * @return the Id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the Id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return "id: " + this.id + " nombre: " + this.nombre + " edad: " + this.edad +".";
    }

}