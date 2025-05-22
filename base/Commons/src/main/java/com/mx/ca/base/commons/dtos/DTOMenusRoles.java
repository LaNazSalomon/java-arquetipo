/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.commons.dtos;

/**
 *
 * @author jbecerril
 */
public class DTOMenusRoles {
     private static final long serialVersionUID = 1L;
   
    private Long idMenuRol;
    
    private DTOMenus idMenu;
   
    private DTORoles idRol;

    public DTOMenusRoles() {
    }

    public DTOMenusRoles(Long idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public Long getIdMenuRol() {
        return idMenuRol;
    }

    public void setIdMenuRol(Long idMenuRol) {
        this.idMenuRol = idMenuRol;
    }

    public DTOMenus getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(DTOMenus idMenu) {
        this.idMenu = idMenu;
    }

    public DTORoles getIdRol() {
        return idRol;
    }

    public void setIdRol(DTORoles idRol) {
        this.idRol = idRol;
    }


    
}
