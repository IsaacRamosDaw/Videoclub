package cliente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.mycompany.videoclub.DAO.IClienteImpl;
import com.mycompany.videoclub.Modelos.Cliente;

/**
 *
 * @author Dante
 */
public class ClienteReadTest {

    public static void main(String[] args) {
        
        System.out.println("--- PRUEBA DE LISTADO DE CLIENTES ---");
        
        IClienteImpl clienteDAO = new IClienteImpl();
        
        try {
            Cliente[] listaClientes = clienteDAO.getAllClient();

            if (listaClientes.length > 0) {
                Cliente primerCliente = listaClientes[0]; 
                
                System.out.println("ID: " + primerCliente.getId());
                System.out.println("Nombre: " + primerCliente.getNombre() + " " + primerCliente.getApellidos());
                System.out.println("DNI: " + primerCliente.getDni());                
                System.out.println("Teléfono: " + primerCliente.getTelefono());
                System.out.println("Categoría: " + primerCliente.getCategoria());
                
            } else {
                System.err.println("La base de datos está conectada, pero no se encontraron clientes.");
            }
            
        } catch (Exception e) {
            System.err.println("Error en la aplicación principal: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
