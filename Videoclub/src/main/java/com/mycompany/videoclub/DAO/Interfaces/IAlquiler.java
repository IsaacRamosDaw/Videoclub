/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.videoclub.DAO.Interfaces;
import com.mycompany.videoclub.Modelos.Alquiler;

/**
 *
 * @author isaac
 */
public interface IAlquiler {
  public boolean createAlquiler();

  public String deleteAlquiler();

  public boolean updateAlquiler();

  public Alquiler getAlquiler();

  public Alquiler[] getAllAlquiler();

  public boolean confirmClient();

}
