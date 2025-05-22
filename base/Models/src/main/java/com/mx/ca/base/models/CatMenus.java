/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 *
 * @author jbecerril
 */
@Entity
@Table(name = "cat_menus")
@NamedQueries({
    @NamedQuery(name = "CatMenus.findAll", query = "SELECT c FROM CatMenus c")})
public class CatMenus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu")
    private Long idMenu;
    @Basic(optional = false)
    
    
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    
    
    @Column(name = "spring_security")
    private String springSecurity;
    @Basic(optional = false)
    
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMenu")
    private List<MenusRoles> menusRolesList;

    public CatMenus() {
    }

    public CatMenus(Long idMenu) {
        this.idMenu = idMenu;
    }

    public CatMenus(Long idMenu, String nombre, String springSecurity, boolean status) {
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

    public List<MenusRoles> getMenusRolesList() {
        return menusRolesList;
    }

    public void setMenusRolesList(List<MenusRoles> menusRolesList) {
        this.menusRolesList = menusRolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatMenus)) {
            return false;
        }
        CatMenus other = (CatMenus) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mx.ca.base.models.CatMenus[ idMenu=" + idMenu + " ]";
    }
    
}
