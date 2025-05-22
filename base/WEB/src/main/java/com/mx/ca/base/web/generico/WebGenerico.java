/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.web.generico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author jbecerril
 */
public class WebGenerico {

    public static String contexto() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    /**
     * Método para mostrar un mensaje de error. Creé este método, ya que el otro
     * no tienen parámetro para mandar el título.
     *
     * @param titulo Título del mensaje de error.
     * @param mensaje Descripción del mensaje de error.
     */
    public static void mensajeError(String titulo, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensaje));
    }

    public static void menajeError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + mensaje, null));
    }

    public static void mensajeWarning(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: " + mensaje, null));
    }

    public static void menajeInformativo(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info: " + mensaje, null));

    }

    public static void redirect(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(contexto() + url);
        } catch (IOException ex) {
            Logger.getLogger(WebGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void subirObjetoSesion(Object obj, String clave) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(clave, obj);

    }

    public static Object bajarObjetoSesion(String clave) {

        if (FacesContext.getCurrentInstance() !=null && FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(clave)) {
            return  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(clave);
        }
        return null;

    }

    public static void eliminarObjetoSesion(String clave) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(clave);

    }

    public static Integer verificaVariable() {
        Integer aux = null;
        if (FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().containsKey("id")) {
            String tmp = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            if (tmp != null) {
                aux = Integer.valueOf(tmp);
            }
        }
        return aux;
    }

}
