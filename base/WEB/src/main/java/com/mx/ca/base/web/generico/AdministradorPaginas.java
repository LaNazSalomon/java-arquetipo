/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.web.generico;

import com.mx.ca.base.models.CatUsuarios;
import com.mx.ca.base.web.generico.WebGenerico;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

@Named
@SessionScoped
public class AdministradorPaginas implements Serializable {


    private String idioma = "es_MX";
 
    private ResourceBundle BUNDLE;
 
    private CatUsuarios usuarioSesion;

    

    public void cambiaPagina(String pagina) {

        WebGenerico.redirect(pagina);
    }

    @PostConstruct
    public void init() {
        //  idioma = "es_MX";
        BUNDLE = ResourceBundle.getBundle("messages.messages_" + idioma);
    }

    public void changeLang(ValueChangeEvent e) {
        Object newValue = e.getNewValue();
        this.idioma = newValue.toString();
        BUNDLE = ResourceBundle.getBundle("messages.messages_" + idioma);
        // UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        // viewRoot.setLocale(new Locale(newValue.toString()));
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale(new Locale(idioma));
        // RequestContext.getCurrentInstance().execute("location.reload();"); 

        PrimeFaces.current().executeScript("location.reload();");

    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public ResourceBundle getBUNDLE() {
        return BUNDLE;
    }

    public void setBUNDLE(ResourceBundle BUNDLE) {
        this.BUNDLE = BUNDLE;
    }

    public CatUsuarios getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(CatUsuarios usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }
    
    

}
