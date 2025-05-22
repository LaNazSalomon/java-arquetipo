/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.base.web.generico;

/**
 *
 * @author jbecerril
 */
public interface CrudCatalogos {
    
    public void guardar();
    public void actualizar();
    public void cancelar();
    public void buscar();
    public void cambiaPaginaAlta();
    public void cambiaPaginaEdicion(Object obj); 
    public void verificamensajes();
    
}
