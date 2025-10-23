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
  public boolean createClient(String nombre, String apellidos, String dni, String telefono) {
    String sql = "INSERT INTO cliente (nombre, apellidos, dni, telefono, categoria) VALUES (?, ?, ?, ?, 'Estandar')";
    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, nombre);
      pstmt.setString(2, apellidos);
      pstmt.setString(3, dni);
      pstmt.setString(4, telefono);

      int filasAfectadas = pstmt.executeUpdate();

      return filasAfectadas > 0;

    } catch (SQLException e) {
      System.err.println("Error al crear cliente: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public String deleteClient(Integer id) {
    String sql = "DELETE FROM cliente WHERE id = ?";

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, id);

      int filasAfectadas = pstmt.executeUpdate();

      if (filasAfectadas > 0) {
        return "Cliente con ID " + id + " eliminado correctamente.";
      } else {
        return "No se encontró ningún cliente con ID " + id + " para eliminar.";
      }

    } catch (SQLException e) {
      System.err.println("Error al eliminar cliente: " + e.getMessage());
      e.printStackTrace();
      return "ERROR: Fallo al eliminar. Mensaje SQL: " + e.getMessage();
    }
  }

  @Override
  public boolean updateClient(Integer id, String username, String apellidos, String dni, String telefono) {
    String sql = "UPDATE cliente SET nombre = ?, apellidos = ?, dni = ?, telefono = ? WHERE id = ?";

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, username);
      pstmt.setString(2, apellidos);
      pstmt.setString(3, dni);
      pstmt.setString(4, telefono);
      pstmt.setInt(5, id);

      int filasAfectadas = pstmt.executeUpdate();

      return filasAfectadas > 0;

    } catch (SQLException e) {
      System.err.println("Error al actualizar cliente: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean updateCategory(Integer id, String categoria) {
    String sql = "UPDATE cliente SET categoria = ? WHERE id = ?";

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, categoria);
      pstmt.setInt(2, id);

      int filasAfectadas = pstmt.executeUpdate();

      return filasAfectadas > 0;

    } catch (SQLException e) {
      System.err.println("Error al actualizar cliente: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Cliente getClient(String username) {
    Cliente cliente = null;
    String sql = "SELECT * FROM cliente where nombre = ?";

    try (
        Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, username);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          cliente = new Cliente(
              rs.getInt("id"),
              rs.getString("nombre"),
              rs.getString("apellidos"),
              rs.getString("dni"),
              rs.getString("telefono"),
              Cliente.Categoria.valueOf(rs.getString("categoria")));
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener cliente por ID: " + e.getMessage());
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      System.err.println("Error de mapeo de categoría (Enum): " + e.getMessage());
      e.printStackTrace();
    }

    if (cliente == null) {
      System.err.println("El cliente devuelve null");
    }

    return cliente;
  }

  @Override
  public Cliente getClient(int id) {
    Cliente cliente = null;
    String sql = "SELECT * FROM cliente where id = ?";

    try (
        Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          cliente = new Cliente(
              rs.getInt("id"),
              rs.getString("nombre"),
              rs.getString("apellidos"),
              rs.getString("dni"),
              rs.getString("telefono"),
              Cliente.Categoria.valueOf(rs.getString("categoria")));
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener cliente por ID: " + e.getMessage());
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      System.err.println("Error de mapeo de categoría (Enum): " + e.getMessage());
      e.printStackTrace();
    }
    if (cliente == null) {
      System.err.println("Se ha devuelto null");
    }
    return cliente;
  }

  @Override
  public Cliente[] getAllClient() {
    String sql = "SELECT * FROM cliente";
    List<Cliente> clientes = new ArrayList<>();

    try (
        Connection conn = BaseDatos.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();) {
      while (rs.next()) {
        Cliente cliente = new Cliente(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("apellidos"),
            rs.getString("dni"),
            rs.getString("telefono"),
            Cliente.Categoria.valueOf(rs.getString("categoria")));
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
  public boolean confirmClient(String username, String password) {
    String sql = "SELECT COUNT(*) FROM cliente WHERE nombre = ? AND password = ?";
    boolean confirmado = false;

    try (
      Connection conn = BaseDatos.conectar();
      PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, username);
      pstmt.setString(2, password);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          confirmado = rs.getInt(1) > 0;
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al confirmar cliente (Login): " + e.getMessage());
      e.printStackTrace();
    }

    return confirmado;
  }

}
