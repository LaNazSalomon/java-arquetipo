/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mx.ca.base.web.managedbeans;

import com.mx.ca.base.UtilCA;
import com.mx.ca.base.models.CatRoles;
import com.mx.ca.base.models.CatUsuarios;
import com.mx.ca.base.models.MenusRoles;
import com.mx.ca.base.web.generico.UtilServicios;
import com.mx.ca.base.web.generico.WebGenerico;
import com.mx.ca.base.web.security.RoleSecurity;
import javax.servlet.http.Cookie;
import java.io.IOException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author jbecerril
 */
@Named(value = "login")
@ViewScoped
public class ControllerLogin extends UtilServicios implements Serializable {

    private String usuario;

    private String password;

    private String password1;

    private String password2;

//    @Getter
//    @Setter
//    public static final String COMPILACION;
    /**
     * Creates a new instance of ControllerLogin
     */
    public ControllerLogin() {
    }

    @PostConstruct
    public void init() {
       
    }

    public void consultaNuevo() {
        try {
            if (getAdministradorPaginas().getUsuarioSesion().getPrimerInicio()) {
                PrimeFaces.current().executeScript("PF('dlgContrasena').show();");
            }
        } catch (Exception ex) {
        }
    }

    public void cierreSesion() {
        PrimeFaces.current().executeScript("PF('WV_SinSesion').show();");
    }

    public void actualizaContrasena() {
        if (UtilCA.validaContrasena(password1) == true) {
            CatUsuarios usuario = getAdministradorPaginas().getUsuarioSesion();
            usuario.setPassword(UtilCA.Encriptar(this.password1));
            usuario.setPrimerInicio(false);
            if (getGenericoService().update(usuario)) {
                salir();
            } else {
                WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("contrasena.nueva.mensaje.error"));
            }

        } else {
            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("contrasena.nueva.incorrecto"));
        }
    }

    public void entrar() {
        try {

            if (!usuario.isEmpty() && !password.isEmpty()) {

                CatUsuarios usuarioBase = getUsuarioService().login(this.usuario, UtilCA.Encriptar(this.password));
                if (usuarioBase != null) {
                    if (usuarioBase.getStatus()) {
                        if (usuarioBase.getIdRol().getStatus()) {
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, password, obtenerGrantedAuthorityDeFuncionalidad(usuarioBase.getIdRol()));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            FacesContext.getCurrentInstance().getExternalContext().redirect(WebGenerico.contexto() + "/auth/dashboard.ca");
                            getAdministradorPaginas().setUsuarioSesion(usuarioBase);

                        } else {
                            WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("login.perfil.inactivo"));
                        }
                    } else {
                        WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("login.inactivo"));
                    }
                } else {
                    WebGenerico.menajeError(getAdministradorPaginas().getBUNDLE().getString("login.incorrecto"));
                }
            }

        } catch (IOException ex) {
//            logger.fatal("Error : ", ex);

        }
    }

    public void salir() {
        try {

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpSession session = request.getSession(false);

            SecurityContextHolder.clearContext();
            session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            for (Cookie cookie : request.getCookies()) {
                cookie.setMaxAge(0);
            }
            //embarquesServices.limpiaSesion();
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/login.ca?faces-redirect=true");

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("getgetAdministradorPaginas()()");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        } catch (IOException ex) {
//            logger.fatal("Error : ", ex);
        }
    }

    public List<GrantedAuthority> obtenerGrantedAuthorityDeFuncionalidad(CatRoles roles) {
        final List<GrantedAuthority> permisos = new ArrayList<GrantedAuthority>();
        RoleSecurity rolSecurity = null;
        for (MenusRoles mr : roles.getMenusRolesList()) {
            rolSecurity = new RoleSecurity();

            rolSecurity.setNombre(mr.getIdMenu().getSpringSecurity());
            permisos.add(rolSecurity);
        }
        return permisos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

}
