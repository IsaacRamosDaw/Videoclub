/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videoclub.Modelos;

/**
 *
 * @author isaac
    CREATE TABLE pelicula (
      id INT PRIMARY KEY AUTO_INCREMENT,
      nombre VARCHAR(150) NOT NULL,
      director VARCHAR(100) NOT NULL,
      lanzamiento VARCHAR(10) NOT NULL,
      genero VARCHAR(50),
      cantidad INT NOT NULL DEFAULT 0
    );
 */
public class Pelicula {
  public Integer id;
  public String nombre;
  public String director;
  // ! He dejado el lanzamiento como string porque no controlo mucho el tema fechas
  public String lanzamiento;
  public String genero;
  public int cantidad;

  public Pelicula(Integer id, String nombre, String director, String lanzamiento, String genero, int cantidad) {
    this.id = id;
    this.nombre = nombre;
    this.director = director;
    this.lanzamiento = lanzamiento;
    this.genero = genero;
    this.cantidad = cantidad;
  }

  public Pelicula(String nombre, String director, String lanzamiento, String genero, int cantidad) {
    this.nombre = nombre;
    this.director = director;
    this.lanzamiento = lanzamiento;
    this.genero = genero;
    this.cantidad = cantidad;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String getDirector() {
    return director;
  }

  public void setLanzamiento(String lanzamiento) {
    this.lanzamiento = lanzamiento;
  }

  public String getLanzamiento() {
    return lanzamiento;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public String getGenero() {
    return genero;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public int getCantidad() {
    return cantidad;
  }
}
