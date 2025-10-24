/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.videoclub.DAO.Interfaces;
import com.mycompany.videoclub.Modelos.Pelicula;

/**
 *
 * @author isaac
 */
public interface IPelicula {
  public boolean createPelicula(String nombre, String director, String lanzamiento, String genero, int cantidad);

  public String deletePelicula(Integer id);

  public boolean deletePeliculaById(int id) throws Exception;

  public boolean updatePelicula(int id, String nombre, String director, String lanzamiento, String genero, int cantidad);

  public Pelicula getPelicula(int id);

  public Pelicula getPelicula(String nombre);

  public Pelicula[] getAllPelicula();

}
