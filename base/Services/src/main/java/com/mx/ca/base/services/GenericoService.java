/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.base.services;

import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface GenericoService {

     public boolean guardar(Object cliente);

    public boolean update(Object cliente);

    public boolean delete(Object cliente);

    public <T> T findByID(Class<T> type, Long id);
    public <T> T findByID(Class<T> type, int id);

    public <T> List<T> findAll(Class<T> type, boolean activos);
    public <T> List<T> findAll(Class<T> type);
    
  public boolean verificaDuplicidad(Class<?> type, String parametro, String valor) ;

}
