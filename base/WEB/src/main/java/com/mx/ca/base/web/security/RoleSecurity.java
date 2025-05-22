/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.web.security;

/**
 *
 * @author jbecerril
 */
import org.springframework.security.core.GrantedAuthority; 

public class RoleSecurity implements GrantedAuthority { 

    private static final long serialVersionUID = 1L;

    private String nombre;

    @Override
    public String getAuthority() {
        return this.nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}