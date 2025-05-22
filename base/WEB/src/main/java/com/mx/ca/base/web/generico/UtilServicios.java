/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.web.generico;



import com.mx.ca.base.services.CatRolesServices;
import com.mx.ca.base.services.CatusuarioService;
import com.mx.ca.base.services.GenericoService;
import java.io.Serializable;
import javax.inject.Inject;


//import javax.faces.annotation.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author jbecerril
 */
@Configuration 
@ComponentScan("com.mx.ca.base.*")
@EnableJpaRepositories("com.mx.ca.base.*")
public class UtilServicios extends SpringBeanAutowiringSupport implements Serializable {

    @Inject    
    private AdministradorPaginas administradorPaginas;


    private Boolean banderaEdicion;
 
    private String valorDuplicidad;
    
    private Long idEdicion;

    @Autowired  
    private  GenericoService genericoService;
    
    @Autowired
    private CatusuarioService usuarioService;
    @Autowired
    private CatRolesServices rolesService;

    public AdministradorPaginas getAdministradorPaginas() {
        return administradorPaginas;
    }

    public void setAdministradorPaginas(AdministradorPaginas administradorPaginas) {
        this.administradorPaginas = administradorPaginas;
    }

    public Boolean getBanderaEdicion() {
        return banderaEdicion;
    }

    public void setBanderaEdicion(Boolean banderaEdicion) {
        this.banderaEdicion = banderaEdicion;
    }

    public String getValorDuplicidad() {
        return valorDuplicidad;
    }

    public void setValorDuplicidad(String valorDuplicidad) {
        this.valorDuplicidad = valorDuplicidad;
    }

    public Long getIdEdicion() {
        return idEdicion;
    }

    public void setIdEdicion(Long idEdicion) {
        this.idEdicion = idEdicion;
    }

    public GenericoService getGenericoService() {
        return genericoService;
    }

    public void setGenericoService(GenericoService genericoService) {
        this.genericoService = genericoService;
    }

    public CatusuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(CatusuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public CatRolesServices getRolesService() {
        return rolesService;
    }

    public void setRolesService(CatRolesServices rolesService) {
        this.rolesService = rolesService;
    }
    
    
    
    
    
    


}
