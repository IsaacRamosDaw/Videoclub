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

import com.mycompany.videoclub.DAO.Interfaces.IPelicula;
import com.mycompany.videoclub.Modelos.Pelicula;
/**
 *
 * @author isaac
 */
public class IPeliculaImpl implements IPelicula {

  /**
   * Crea una nueva película en la base de datos.
   */
  @Override
  public boolean createPelicula(String nombre, String director, String lanzamiento, String genero, int cantidad) {
    String sql = "INSERT INTO pelicula (nombre, director, lanzamiento, genero, cantidad) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, nombre);
      pstmt.setString(2, director);
      pstmt.setString(3, lanzamiento);
      pstmt.setString(4, genero);
      pstmt.setInt(5, cantidad);

      int filasAfectadas = pstmt.executeUpdate();

      return filasAfectadas > 0;

    } catch (SQLException e) {
      System.err.println("Error al crear película: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Elimina una película por su ID.
   */
  @Override
  public String deletePelicula(int id) {
    String sql = "DELETE FROM pelicula WHERE id = ?";

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, id);

      int filasAfectadas = pstmt.executeUpdate();

      if (filasAfectadas > 0) {
        return "Película con ID " + id + " eliminada correctamente.";
      } else {
        return "No se encontró ninguna película con ID " + id + " para eliminar.";
      }

    } catch (SQLException e) {
      System.err.println("Error al eliminar película: " + e.getMessage());
      e.printStackTrace();
      return "ERROR: Fallo al eliminar. Mensaje SQL: " + e.getMessage();
    }
  }

  /**
   * Actualiza los datos de una película.
   */
  @Override
  public boolean updatePelicula(int id, String nombre, String director, String lanzamiento, String genero, int cantidad) {
    String sql = "UPDATE pelicula SET nombre = ?, director = ?, lanzamiento = ?, genero = ?, cantidad = ? WHERE id = ?";

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, nombre);
      pstmt.setString(2, director);
      pstmt.setString(3, lanzamiento);
      pstmt.setString(4, genero);
      pstmt.setInt(5, cantidad);
      pstmt.setInt(6, id);

      int filasAfectadas = pstmt.executeUpdate();

      return filasAfectadas > 0;

    } catch (SQLException e) {
      System.err.println("Error al actualizar película: " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Obtiene una película por su ID.
   */
  @Override
  public Pelicula getPelicula(int id) {
    Pelicula pelicula = null;
    String sql = "SELECT * FROM pelicula WHERE id = ?";

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setInt(1, id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          pelicula = new Pelicula(
              rs.getInt("id"),
              rs.getString("nombre"),
              rs.getString("director"),
              rs.getString("lanzamiento"),
              rs.getString("genero"),
              rs.getInt("cantidad"));
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener película por ID: " + e.getMessage());
      e.printStackTrace();
    }
    return pelicula;
  }

  /**
   * Obtiene una película por su nombre.
   */
  @Override
  public Pelicula getPelicula(String nombre) {
    Pelicula pelicula = null;
    String sql = "SELECT * FROM pelicula WHERE nombre = ?";

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      pstmt.setString(1, nombre);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          pelicula = new Pelicula(
              rs.getInt("id"),
              rs.getString("nombre"),
              rs.getString("director"),
              rs.getString("lanzamiento"),
              rs.getString("genero"),
              rs.getInt("cantidad"));
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al obtener película por nombre: " + e.getMessage());
      e.printStackTrace();
    }
    return pelicula;
  }

  /**
   * Obtiene todas las películas en la base de datos.
   */
  @Override
  public Pelicula[] getAllPelicula() {
    String sql = "SELECT * FROM pelicula";
    List<Pelicula> peliculas = new ArrayList<>();

    try (Connection conn = BaseDatos.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Pelicula pelicula = new Pelicula(
              rs.getInt("id"),
              rs.getString("nombre"),
              rs.getString("director"),
              rs.getString("lanzamiento"),
              rs.getString("genero"),
              rs.getInt("cantidad"));
          peliculas.add(pelicula);
        }
      }

    } catch (SQLException e) {
      System.err.println("Error al listar todas las películas: " + e.getMessage());
      e.printStackTrace();
    }
    return peliculas.toArray(new Pelicula[0]);
  }
}