package cliente;


import com.mycompany.videoclub.DAO.IClienteImpl;
import com.mycompany.videoclub.Modelos.Cliente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dante
 */
public class ClienteCreateTest {

    public static void main(String[] args) {
        
        System.out.println("--- PRUEBA DE CREACIÓN DE NUEVO CLIENTE ---");
        
        IClienteImpl clienteDAO = new IClienteImpl();
        
        String nombre = "Pedro";
        String apellidos = "Gómez Pérez";
        String dni = "12345678X";
        String telefono = "600111222";

        try {
            boolean creacionExitosa = clienteDAO.createClient(nombre, apellidos, dni, telefono);

            if (creacionExitosa) {
                System.out.println("\nCREACIÓN EXITOSA: Cliente '" + nombre + "' insertado.");
                
                Cliente nuevoCliente = clienteDAO.getClient(nombre);
                
                if (nuevoCliente != null) {
                    System.out.println("\n--- VERIFICACIÓN DE LECTURA ---");
                    System.out.println("ID asignado: " + nuevoCliente.getId());
                    System.out.println("DNI leído: " + nuevoCliente.getDni());
                    System.out.println("Categoría: " + nuevoCliente.getCategoria());
                } else {
                    System.err.println("WARNING: El cliente se insertó, pero falló la lectura de verificación.");
                }

            } else {
                System.err.println("FALLIDA: El método createClient devolvió false (0 filas afectadas).");
            }
            
        } catch (Exception e) {
            // Captura de cualquier RuntimeException, como la que podría lanzar BaseDatos.conectar()
            System.err.println("ERROR FATAL durante la prueba de creación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
