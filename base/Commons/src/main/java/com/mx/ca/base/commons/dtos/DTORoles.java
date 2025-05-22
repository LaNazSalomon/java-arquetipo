/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.commons.dtos;

import java.util.List;

/**
 *
 * @author jbecerril
 */
public class DTORoles {

    private static final long serialVersionUID = 1L;

    private Long idRol;

    private String nombre;

    private String descripcion;

    private boolean status;

    private List<DTOMenusRoles> menusRolesList;
    private List<DTOUsuarios> catUsuariosList;

    public DTORoles() {
    }

    public DTORoles(Long idRol) {
        this.idRol = idRol;
    }

    public DTORoles(Long idRol, String nombre, boolean status) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DTOMenusRoles> getMenusRolesList() {
        return menusRolesList;
    }

    public void setMenusRolesList(List<DTOMenusRoles> menusRolesList) {
        this.menusRolesList = menusRolesList;
    }

    public List<DTOUsuarios> getDTOUsuariosList() {
        return catUsuariosList;
    }

    public void setDTOUsuariosList(List<DTOUsuarios> catUsuariosList) {
        this.catUsuariosList = catUsuariosList;
    }

}
