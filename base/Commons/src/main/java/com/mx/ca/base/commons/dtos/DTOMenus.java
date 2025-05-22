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
public class DTOMenus {

    private static final long serialVersionUID = 1L;

    private Long idMenu;

    private String nombre;

    private String springSecurity;

    private boolean status;

    private List<DTOMenusRoles> menusRolesList;

    public DTOMenus() {
    }

    public DTOMenus(Long idMenu) {
        this.idMenu = idMenu;
    }

    public DTOMenus(Long idMenu, String nombre, String springSecurity, boolean status) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.springSecurity = springSecurity;
        this.status = status;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSpringSecurity() {
        return springSecurity;
    }

    public void setSpringSecurity(String springSecurity) {
        this.springSecurity = springSecurity;
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
    
    

}
