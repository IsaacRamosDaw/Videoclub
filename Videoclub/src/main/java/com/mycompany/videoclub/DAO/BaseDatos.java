/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videoclub.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author isaac
 */
public class BaseDatos {

  private static final String URL = "jdbc:mysql://localhost:3306/videoclub";
  private static final String USUARIO = "root";
  private static final String PASSWORD = "admin";

  public static Connection conectar() {

    try {
      System.out.println("Conectando...");
      return DriverManager.getConnection(URL, USUARIO, PASSWORD);

    } catch (SQLException e) {
      System.err.println("FATAL ERROR: Fallo al conectar con la base de datos.");
      System.err.println("Mensaje SQL: " + e.getMessage());
      throw new RuntimeException("Error al conectar con la base de datos", e);

    }
  }

  public static void cerrarConexion(Connection conexion) {
    try {
      if (conexion != null && !conexion.isClosed()) {
        conexion.close();
      }

    } catch (SQLException e) {
      throw new RuntimeException("Error al cerrar la conexión", e);

    }

  }

  public static void testearConexion() {
    try (Connection conn = conectar()) {
      System.out.println("Conexión a la base de datos exitosa.");
    } catch (RuntimeException e) {
      System.err.println("rueba fallida.");
    } catch (SQLException e) {
    }
  }

  public static void main(String[] args) {
    testearConexion();
  }
}
