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
public class ClienteUpdateTest {

  public static void main(String[] args) {

    System.out.println("--- PRUEBA DE ACTUALIZACIÓN DE CLIENTE (ID: 1) ---");

    IClienteImpl clienteDAO = new IClienteImpl();
    final Integer ID = 1;

    String nuevoNombre = "Anna";
    String nuevosApellidos = "Ruiz Sánchez";
    String nuevoDni = "99887766Y";
    String nuevoTelefono = "699333444";

    try {
      Cliente clienteAntes = clienteDAO.getClient(ID);
      //! Opcional, solo para ver los datos antes y después 
      if (clienteAntes != null) {
        System.out.println("\n--- ESTADO ANTERIOR ---");
        System.out.println("Nombre: " + clienteAntes.getNombre() + " | DNI: " + clienteAntes.getDni());
      } else {
        System.err.println(
          "WARNING: No se encontró el cliente con ID 1. La prueba de UPDATE procederá, pero podría fallar.");
      }

      boolean actualizacionExitosa = clienteDAO.updateClient(ID, nuevoNombre, nuevosApellidos, nuevoDni, nuevoTelefono);

      if (actualizacionExitosa) {
        System.out.println("\n ACTUALIZACIÓN EXITOSA: Cliente con ID " + ID + " modificado.");

        Cliente clienteDespues = clienteDAO.getClient(ID);

        if (clienteDespues != null) {
          System.out.println("\n--- ESTADO POSTERIOR (VERIFICACIÓN) ---");
          System.out.println("Nuevo Nombre: " + clienteDespues.getNombre());
          System.out.println("Nuevo Apellido: " + clienteDespues.getApellidos());
          System.out.println("Nuevo DNI: " + clienteDespues.getDni());
          System.out.println("Nuevo Teléfono: " + clienteDespues.getTelefono());
        } else {
          System.err.println(
              "WARNING: La actualización fue reportada como exitosa, pero falló la lectura de verificación.");
        }

      } else {
        System.err.println(
            "\n FALLO EN LA ACTUALIZACIÓN: El método updateClient devolvió false (0 filas afectadas).");
      }

    } catch (Exception e) {
      System.err.println("ERROR FATAL durante la prueba de actualización: " + e.getMessage());
      e.printStackTrace();
    }
  }
}