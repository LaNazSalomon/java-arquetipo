/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.web.managedbeans;

import com.mx.ca.base.UtilCA;
import com.mx.ca.base.models.CatMenus;
import com.mx.ca.base.models.CatRoles;
import com.mx.ca.base.models.MenusRoles;
import com.mx.ca.base.web.generico.CrudCatalogos;
import com.mx.ca.base.web.generico.UtilServicios;
import com.mx.ca.base.web.generico.WebGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

/**
 *
 * @author jbecerril
 */
@Named(value = "controllerCatRoles")
@ViewScoped
public class ControllerCatRoles extends UtilServicios implements CrudCatalogos {

 
    private CatRoles nuevo;
  
    private List<CatRoles> listaCatalogo;
  
    private List<CatRoles> filtroCatalogo;

    private DualListModel<CatMenus> listaMenus;

    private String urlbase = "/auth/administracion/roles/";

    @PostConstruct
    public void init() {
        Integer id = WebGenerico.verificaVariable();
        verificamensajes();
        listaMenus = new DualListModel<>();

        if (id != null) {
            switch (id) {
                case 0:
                    buscar();
                    break;
                case 1:
                    buscarMenusDisponibles();
                    setBanderaEdicion(false);
                    nuevo = new CatRoles();

                    break;
                case 2: {

                    String clave = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("var");
                    nuevo = (CatRoles) WebGenerico.bajarObjetoSesion(clave);

                    nuevo.getMenusRolesList().stream().forEach(aux -> {
                        listaMenus.getTarget().add(aux.getIdMenu());

                    });
                    buscarMenusDisponibles();

                    setBanderaEdicion(true);
                    setValorDuplicidad(nuevo.getNombre());
                }

                break;
            }
        }

    }

    public void llenadoMenusRoles() {
        if (!listaMenus.getTarget().isEmpty()) {
            nuevo.setMenusRolesList(new ArrayList<>());
            listaMenus.getTarget().stream().forEach(aux -> {
                MenusRoles r = new MenusRoles();
                r.setIdMenu(aux);
                r.setIdRol(nuevo);
                nuevo.getMenusRolesList().add(r);
            });

        }
    }

    public void buscarMenusDisponibles() {
        List<CatMenus> base = new ArrayList<>();
        base = getRolesService().buscarTodosMenusDisponibles();
        if (listaMenus != null && listaMenus.getTarget() != null && !listaMenus.getTarget().isEmpty()) {
            List<Long> ids = new ArrayList<>();
            listaMenus.getTarget().stream().forEach(aux -> {
                ids.add(aux.getIdMenu());
            });
            base = getRolesService().buscarMenusDisponibles(ids);
            listaMenus.setSource(base);
        } else {
            listaMenus = new DualListModel<>(base, new ArrayList<>());
        }
    }

    @Override
    public void verificamensajes() {

        if (WebGenerico.bajarObjetoSesion("informativo") != null) {
            String informativo = (String) WebGenerico.bajarObjetoSesion("informativo");
            WebGenerico.menajeInformativo(informativo);
            WebGenerico.eliminarObjetoSesion("informativo");

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
            if (!nuevo.getNombre().equals(getValorDuplicidad())) {
                if (!getGenericoService().verificaDuplicidad(CatRoles.class, "nombre", nuevo.getNombre())) {
                    actualizar();
                } else {

                    WebGenerico.mensajeError("Error", "Ya existe un registro con el identificador: " + nuevo.getNombre());
                }
            } else {
                actualizar();
            }
        } else {
            if (!getGenericoService().verificaDuplicidad(CatRoles.class, "nombre", nuevo.getNombre())) {
                llenadoMenusRoles();
                if (getGenericoService().guardar(nuevo)) {
                    WebGenerico.subirObjetoSesion("Registro Guardado correctamente", "informativo");

                    WebGenerico.redirect(urlbase + "bandeja.ca?id=0");
                }
            } else {
                WebGenerico.mensajeError("Error", "Ya existe un registro con el identificador: " + nuevo.getNombre());

            }
        }
    }

    @Override
    public void actualizar() {
        llenadoMenusRoles();
        if (getGenericoService().update(nuevo)) {
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
        listaCatalogo = getGenericoService().findAll(CatRoles.class);
    }

    @Override
    public void cambiaPaginaAlta() {
        WebGenerico.redirect(urlbase + "formulario.ca?id=1");
    }

    @Override
    public void cambiaPaginaEdicion(Object obj) {
        nuevo = (CatRoles) obj;
        WebGenerico.subirObjetoSesion(obj, nuevo.getIdRol().toString());
        WebGenerico.redirect(urlbase + "formulario.ca?id=2&var=" + nuevo.getIdRol());
    }

    public CatRoles getNuevo() {
        return nuevo;
    }

    public void setNuevo(CatRoles nuevo) {
        this.nuevo = nuevo;
    }

    public List<CatRoles> getListaCatalogo() {
        return listaCatalogo;
    }

    public void setListaCatalogo(List<CatRoles> listaCatalogo) {
        this.listaCatalogo = listaCatalogo;
    }

    public List<CatRoles> getFiltroCatalogo() {
        return filtroCatalogo;
    }

    public void setFiltroCatalogo(List<CatRoles> filtroCatalogo) {
        this.filtroCatalogo = filtroCatalogo;
    }

    public DualListModel<CatMenus> getListaMenus() {
        return listaMenus;
    }

    public void setListaMenus(DualListModel<CatMenus> listaMenus) {
        this.listaMenus = listaMenus;
    }
    
    

}
