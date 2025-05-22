/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.base.services;

import com.mx.ca.base.models.CatMenus;
import java.util.List;

/**
 *
 * @author jbecerril
 */
public interface CatRolesServices {

    List<CatMenus> buscarMenusDisponibles(List<Long> ids);

    List<CatMenus> buscarTodosMenusDisponibles();

}
