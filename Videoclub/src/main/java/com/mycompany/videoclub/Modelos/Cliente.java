/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.videoclub.Modelos;

/**
 *
 * @author isaac

  CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(15) UNIQUE NOT NULL, 
    telefono VARCHAR(20),
    categoria VARCHAR(20) NOT NULL
  ;
 */
public class Cliente {
  private Integer id;
  private String dni;
  private String nombre;
  private String apellidos;
  private String telefono;
  private Categoria categoria;

  public Cliente(Integer id, String nombre, String apellidos, String dni, String telefono, Categoria categoria){
    this.id = id;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.dni = dni;
    this.telefono = telefono;
    this.categoria = categoria;
  }

  public Cliente(String nombre, String apellidos, String dni, String telefono, Categoria categoria ){
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.dni = dni;
    this.telefono = telefono;
    this.categoria = categoria;
  }

  public Cliente(){
  }
  

  public enum Categoria {
    Oro,
    Estandar
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

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getDni() {
    return dni;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Categoria getCategoria() {
    return categoria;
  }

}
