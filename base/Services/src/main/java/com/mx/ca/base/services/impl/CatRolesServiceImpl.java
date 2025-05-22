/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.services.impl;

import com.mx.ca.base.models.CatMenus;
import com.mx.ca.base.repositorys.CatRolesRepository;
import com.mx.ca.base.services.CatRolesServices;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service("catRolesServices")
public class CatRolesServiceImpl implements CatRolesServices, Serializable {

    @Autowired
    CatRolesRepository repository;

    @Override
    public List<CatMenus> buscarMenusDisponibles(List<Long> ids) {
        return repository.buscarMenusDisponibles(ids);
    }

    @Override
    public List<CatMenus> buscarTodosMenusDisponibles() {
        return repository.buscarTodosMenusDisponibles();
    }

}
