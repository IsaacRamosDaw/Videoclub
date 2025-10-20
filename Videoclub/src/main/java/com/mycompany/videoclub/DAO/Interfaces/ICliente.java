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
  public boolean agregarCliente(Cliente u);

  public String eliminarCliente(Integer id);

  public boolean actualizarCliente(Integer id, Cliente u);

  public Cliente listarClientes(String username);

  public Cliente[] listarAllClientes();

  public boolean validarCliente(String username);

}
