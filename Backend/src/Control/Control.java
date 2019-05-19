/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Datos.GlobalException;
import Datos.NoDataException;
import Datos.ServicioAgencia;
import Datos.ServicioCliente;
import Entidades.Agencia;
import Entidades.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author david
 */
public class Control {
    private ServicioCliente servicioCliente;
    private ServicioAgencia servicioAgencia;
    private static Control uniqueInstance;
    public static Control instance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Control();
        }
        return uniqueInstance;
    }
    public Control()
    {
        servicioCliente = new ServicioCliente();
        servicioAgencia = new ServicioAgencia();
    }
    
    //Clientes
    public void insertarCliente(Cliente cliente) throws Exception{
        servicioCliente.insertarCliente(cliente);
    }
    
   public void eliminarCliente(String cedula) throws Exception{
        servicioCliente.eliminarCliente(cedula);
    }
    
    public ArrayList<Cliente> listarClientes() throws GlobalException, NoDataException{
        return servicioCliente.listarClientes();
    }
    
    public void modificarCliente(Cliente cliente) throws Exception{
        servicioCliente.editarCliente(cliente);
    }
        
    public List<Cliente> buscarClienteCedula(String cedula) throws GlobalException, NoDataException{
        return (List<Cliente>)servicioCliente.buscarClienteCedula(cedula);
    }
    
    //Agencias
    public void insertarAgencia(Agencia agencia) throws Exception{
        servicioAgencia.insertarAgencia(agencia);
    }
    
    public void modificarAgencia(Agencia agencia) throws Exception{
        servicioAgencia.editarAgencia(agencia);
    }
    
   public void eliminarAgencia(String codigo) throws Exception{
        servicioAgencia.eliminarAgencia(codigo);
    }
    
    public ArrayList<Agencia> listarAgencias() throws GlobalException, NoDataException{
        return servicioAgencia.listarAgencias();
    }
    
    
}
