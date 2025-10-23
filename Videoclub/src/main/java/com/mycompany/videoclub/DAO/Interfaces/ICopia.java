/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.videoclub.DAO.Interfaces;

import com.mycompany.videoclub.Modelos.Copia;

/**
 *
 * @author isaac
 */
public interface ICopia {
  public boolean createCopia();

  public String deleteCopia();

  public boolean updateCopia();

  public Copia getCopia();

  public Copia[] getAllCopia();
}
