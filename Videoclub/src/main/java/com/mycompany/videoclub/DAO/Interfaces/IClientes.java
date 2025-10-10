/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.videoclub.DAO.Interfaces;

import com.mycompany.videoclub.Modelos.Clientes;

/**
 *
 * @author isaac
 */
public interface IClientes {
  public boolean agregarCliente(Clientes u);

  public String eliminarCliente(Integer id);

  public boolean actualizarCliente(Integer id, Clientes u);

  public Clientes listarClientes(String username);

  public boolean validarCliente(String username);

}
