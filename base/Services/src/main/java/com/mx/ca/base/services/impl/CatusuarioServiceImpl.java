/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.services.impl;

import com.mx.ca.base.models.CatUsuarios;
import com.mx.ca.base.repositorys.CatUsuariosRepository;
import com.mx.ca.base.services.CatusuarioService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service("CatusuarioService")
public class CatusuarioServiceImpl implements CatusuarioService, Serializable {

    @Autowired
    CatUsuariosRepository repository;

    @Override
    public CatUsuarios login(String user, String pass) {
        return repository.login(user, pass);
    }

}
