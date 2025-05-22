/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.base.services;

/**
 *
 * @author jbecerril
 */
public interface GeneraPeticionRestService {

    public <T> T generaPeticionGet(String url, Class<T> definitionRef);

}
