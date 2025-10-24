/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.videoclub.DAO.Interfaces;

import com.mycompany.videoclub.Modelos.Cliente;

/**
 *
 * @author isaac
 */
public interface ICliente {
  public boolean createClient(String nombre, String apellidos, String dni, String telefono);

  public String deleteClient(Integer id);

  public boolean deleteClient(int id) throws Exception;

  public boolean updateClient(Integer id, String username, String apellidos, String dni, String telefono);

  public boolean updateCategory(Integer id, String categoria);

  public Cliente getClient(String username);

  public Cliente getClient(int id);

  public Cliente[] getAllClient();

  public boolean confirmClient(String username, String password);

}
