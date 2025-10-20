/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videoclub.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.videoclub.DAO.Interfaces.ICliente;
import com.mycompany.videoclub.Modelos.Cliente;

/**
 *
 * @author isaac
 */
public class IClienteImpl implements ICliente {

  @Override
  public boolean agregarCliente(Cliente u) {
    throw new UnsupportedOperationException("Unimplemented method 'agregarCliente'");
  }

  @Override
  public String eliminarCliente(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'eliminarCliente'");
  }

  @Override
  public boolean actualizarCliente(Integer id, Cliente u) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'actualizarCliente'");
  }

  @Override
  public Cliente listarClientes(String username) {
    throw new UnsupportedOperationException("Unimplemented method 'listaCLIENTES'");

  }

  @Override
  public Cliente[] listarAllClientes() {
    String sql = "SELECT * FROM cliente";
    List<Cliente> clientes = new ArrayList<>();

    try (
        Connection conn = BaseDatos.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
      ){
      while (rs.next()) {
        Cliente cliente = new Cliente(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("apellidos"),
            rs.getString("dni"),
            rs.getString("telefono"),
            Cliente.Categoria.valueOf(rs.getString("categoria"))
        );
        clientes.add(cliente);
      }

    } catch (SQLException e) { 
        System.err.println("Error al listar todos los clientes: " + e.getMessage());
        e.printStackTrace();
    } catch (IllegalArgumentException e) {
        System.err.println("Error de mapeo de categoría (Enum): " + e.getMessage());
        e.printStackTrace();
    }
    return clientes.toArray(new Cliente[0]);
  }

  @Override
  public boolean validarCliente(String username) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'validarCliente'");
  }
  // @Override
  // public Cliente[] listarAllClientes() {
  //   String sql = "SELECT * FROM cliente";
  //   ArrayList<Cliente> clientes = new ArrayList<>();

  //   try (
  //       Connection conn = BaseDatos.conectar();
  //       java.sql.Statement stmt = conn.createStatement();
  //       java.sql.ResultSet rs = stmt.executeQuery(sql);
  //     ){
  //     while (rs.next()) {
  //       Cliente cliente = new Cliente(
  //           rs.getInt("id"),
  //           rs.getString("dni"),
  //           rs.getString("nombre"),
  //           rs.getString("apellidos"),
  //           rs.getString("telefono"),
  //           Cliente.Categoria.valueOf(rs.getString("categoria"))
  //       );
  //       clientes.add(cliente);
  //     }

  //   } catch (SQLException e) { 
  //       System.err.println("Error al listar todos los clientes: " + e.getMessage());
  //       e.printStackTrace();
  //   } catch (IllegalArgumentException e) {
  //       System.err.println("Error de mapeo de categoría (Enum): " + e.getMessage());
  //       e.printStackTrace();
  //   }
  //   return clientes.toArray(new Cliente[0]);
  // }

  // @Override
  // public boolean validarCliente(String username) {
  //   // TODO Auto-generated method stub
  //   throw new UnsupportedOperationException("Unimplemented method 'validarCliente'");
  // }

}
