/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import com.mycompany.videoclub.DAO.IClienteImpl;
import com.mycompany.videoclub.Modelos.Cliente;

/**
 *
 * @author Dante
 */
public class ClienteReadAllTest {

  public static void main(String[] args) {

    System.out.println("--- PRUEBA DE LECTURA DE TODOS LOS CLIENTES (getAllClient) ---");

    IClienteImpl clienteDAO = new IClienteImpl();

    try {
      Cliente[] listaClientes = clienteDAO.getAllClient();

      if (listaClientes == null || listaClientes.length == 0) {
        System.out.println("\n La base de datos está conectada, pero no se encontraron clientes.");
        return;
      }

      System.out.println("\n Lectura exitosa. Clientes encontrados: " + listaClientes.length);
      System.out.println("------------------------------------------------------------------");

      for (Cliente cliente : listaClientes) {
        System.out.println("ID: " + cliente.getId() +
            " | Nombre: " + cliente.getNombre() +
            " " + cliente.getApellidos() +
            " | DNI: " + cliente.getDni() +
            " | Cat.: " + cliente.getCategoria());
      }

      System.out.println("------------------------------------------------------------------");

    } catch (Exception e) {
      System.err.println("\n ERROR FATAL durante la prueba de lectura: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
