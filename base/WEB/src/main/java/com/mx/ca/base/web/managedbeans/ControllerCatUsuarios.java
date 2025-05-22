/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mx.ca.base.web.managedbeans;

import com.mx.ca.base.Constantes;
import com.mx.ca.base.UtilCA;
import com.mx.ca.base.models.CatRoles;
import com.mx.ca.base.models.CatUsuarios;
import com.mx.ca.base.web.generico.CrudCatalogos;
import com.mx.ca.base.web.generico.UtilServicios;
import com.mx.ca.base.web.generico.WebGenerico;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.Serializable;
import org.primefaces.PrimeFaces;

/**
 *
 * @author jbecerril
 */
@Named(value = "controllerCatUsuarios")
@ViewScoped
public class ControllerCatUsuarios extends UtilServicios implements Serializable, CrudCatalogos {

    /**
     * Creates a new instance of ControllerCatUsuarios
     */

    private CatUsuarios nuevo;

    private List<CatUsuarios> listaCatalogo;

    private List<CatUsuarios> filtroCatalogo;

    private List<CatRoles> listaRoles;

    private String urlbase = "/auth/administracion/usuarios/";
 
    private boolean banderaContrasena;

    public ControllerCatUsuarios() {
    }

    @PostConstruct
    public void init() {
        Integer id = WebGenerico.verificaVariable();
        verificamensajes();

        if (id != null) {
            switch (id) {
                case 0:
                    buscar();
                    break;
                case 1:
                    setBanderaEdicion(false);
                    nuevo = new CatUsuarios();
                    buscarRoles();

                    break;
                case 2: {
                    buscarRoles();

                    String clave = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("var");
                    nuevo = (CatUsuarios) WebGenerico.bajarObjetoSesion(clave);
                    this.banderaContrasena = true;
                    setBanderaEdicion(true);
                    setValorDuplicidad(nuevo.getNombres() + nuevo.getApellidoPaterno() + nuevo.getApellidoMaterno());

                }

                break;
            }
        }

    }

    public void buscarRoles() {
        listaRoles = getGenericoService().findAll(CatRoles.class, true);
    }

    public void calculaUsuaro() {

        String nombre[] = nuevo.getNombres().split(" ");

        nuevo.setUsername(nombre[0] + "." + nuevo.getApellidoPaterno());

    }

    public void reestablecer() {
        this.banderaContrasena = false;
        nuevo.setPassTmp(UtilCA.generateRandomPassword(8)); 
        nuevo.setPassword(UtilCA.Encriptar(nuevo.getPassTmp())); 
        nuevo.setUpdate(true); 
        nuevo.setPrimerInicio(true);
        WebGenerico.menajeInformativo("Se enviara una contraseña temporal cuando guarde los cambios");

    }

    @Override
    public void verificamensajes() {

        if (WebGenerico.bajarObjetoSesion("informativo") != null) {
            String informativo = (String) WebGenerico.bajarObjetoSesion("informativo");
            WebGenerico.menajeInformativo(informativo);
            WebGenerico.eliminarObjetoSesion("informativo");
            PrimeFaces.current().ajax().update("formGlobal:mensjaeGlobal");

        }

        if (WebGenerico.bajarObjetoSesion("error") != null) {
            String error = (String) WebGenerico.bajarObjetoSesion("error");
            WebGenerico.menajeError(error);
            WebGenerico.eliminarObjetoSesion("error");

        }

    }

    @Override
    public void guardar() {
        if (getBanderaEdicion()) {
            if (!(nuevo.getNombres() + nuevo.getApellidoPaterno() + nuevo.getApellidoMaterno()).equals(getValorDuplicidad())) {
//                if (!getGenericoCatalogos().buscarUsuarioDuplicado(nuevo.getNombres(), nuevo.getApellidoPaterno(), nuevo.getApellidoMaterno())) {
                if (true) {
                    actualizar();
                } else {

                    WebGenerico.mensajeError("Error", "Ya existe un registro con el identificador: " + nuevo.getNombres());
                }
            } else {
                actualizar();
            }
        } else {
            //if (!getGenericoService().buscarUsuarioDuplicado(nuevo.getNombres(), nuevo.getApellidoPaterno(), nuevo.getApellidoMaterno())) {
            if (true) {
                nuevo.setPrimerInicio(true);
                String passtmp = UtilCA.generateRandomPassword(8);
                nuevo.setPassword(UtilCA.Encriptar(passtmp));

                if (getGenericoService().guardar(nuevo)) {
                   // getEnvioNotificaciones().sendMailWithAttachments(Constantes.TEXTO_NUEVO_USUARIO_ASUNTO, Constantes.TEXTO_NUEVO_USUARIO + passtmp, nuevo.getEmail());
                    WebGenerico.subirObjetoSesion("Registro Guardado correctamente, se enviara un email con la contraseña temporal", "informativo");

                    WebGenerico.redirect(urlbase + "bandeja.ca?id=0");
                }
            } else {
                WebGenerico.mensajeError("Error", "Ya existe un registro con el identificador: " + nuevo.getNombres());

            }
        }
    }

    @Override
    public void actualizar() {
        if (getGenericoService().update(nuevo)) {
            if (nuevo.isUpdate()) {

               // getEnvioNotificaciones().sendMailWithAttachments(Constantes.TEXTO_RECUPERACION_USUARIO_ASUNTO, Constantes.TEXTO_NUEVO_USUARIO + nuevo.getPassTmp(), nuevo.getEmail());

            }

            WebGenerico.subirObjetoSesion("Registro modificado correctamente", "informativo");

            WebGenerico.redirect(urlbase + "bandeja.ca?id=0");
        } else {
            WebGenerico.menajeError("No se pudo modificar el registro, intentelo nuevamente");
        }
    }

    @Override
    public void cancelar() {
        WebGenerico.redirect(urlbase + "bandeja.ca?id=0");
    }

    @Override
    public void buscar() {
        listaCatalogo = getGenericoService().findAll(CatUsuarios.class, true);
    }

    @Override
    public void cambiaPaginaAlta() {
        WebGenerico.redirect(urlbase + "formulario.ca?id=1");
    }

    @Override
    public void cambiaPaginaEdicion(Object obj) {
        nuevo = (CatUsuarios) obj;
        WebGenerico.subirObjetoSesion(obj, nuevo.getIdUsuario().toString());
        WebGenerico.redirect(urlbase + "formulario.ca?id=2&var=" + nuevo.getIdUsuario());
    }

    public CatUsuarios getNuevo() {
        return nuevo;
    }

    public void setNuevo(CatUsuarios nuevo) {
        this.nuevo = nuevo;
    }

    public List<CatUsuarios> getListaCatalogo() {
        return listaCatalogo;
    }

    public void setListaCatalogo(List<CatUsuarios> listaCatalogo) {
        this.listaCatalogo = listaCatalogo;
    }

    public List<CatUsuarios> getFiltroCatalogo() {
        return filtroCatalogo;
    }

    public void setFiltroCatalogo(List<CatUsuarios> filtroCatalogo) {
        this.filtroCatalogo = filtroCatalogo;
    }

    public List<CatRoles> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<CatRoles> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public boolean isBanderaContrasena() {
        return banderaContrasena;
    }

    public void setBanderaContrasena(boolean banderaContrasena) {
        this.banderaContrasena = banderaContrasena;
    }
    
    

}
