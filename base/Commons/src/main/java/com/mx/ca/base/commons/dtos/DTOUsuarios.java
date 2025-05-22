/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.commons.dtos;

/**
 *
 * @author jbecerril
 */
public class DTOUsuarios {
    
    private static final long serialVersionUID = 1L;
   
    private Long idUsuario;
  
    private String nombres;
   
    private String apellidoPaterno;
   
    private String apellidoMaterno;
   
    private String username;
   
    private String password;
   
    private boolean status;
   
    private DTORoles idRol;

    public DTOUsuarios() {
    }

    public DTOUsuarios(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public DTOUsuarios(Long idUsuario, String nombres, String apellidoPaterno, String username, String password, boolean status) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DTORoles getIdRol() {
        return idRol;
    }

    public void setIdRol(DTORoles idRol) {
        this.idRol = idRol;
    }

    
}
