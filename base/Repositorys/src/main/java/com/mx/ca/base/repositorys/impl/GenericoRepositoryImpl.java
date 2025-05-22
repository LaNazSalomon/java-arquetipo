/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.repositorys.impl;

/**
 *
 * @author jbecerril
 */

import com.mx.ca.base.repositorys.GenericoRepository;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbecerril
 */
@Repository("genericoRepository")
public class GenericoRepositoryImpl extends SimpleRepository implements GenericoRepository {

    private static final Logger logger = LogManager.getLogger(GenericoRepositoryImpl.class.getName());

    @Override
    @Transactional
    public boolean guardar(Object cliente) {
        boolean respuesta = false;
        try {
            getSession().save(cliente);
            
            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean update(Object cliente) {
        boolean respuesta = false;
        try {
            getSession().saveOrUpdate(cliente);

            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
            respuesta = false;
        }
        return respuesta;
    }

    @Override
    @Transactional
    public boolean delete(Object cliente) {
        boolean respuesta = false;
        try {
            getSession().delete(cliente);

            respuesta = true;
        } catch (Exception e) {
            logger.error(e);
            respuesta = false;
        }
        return respuesta;
    }

    @Override
     @Transactional
    public boolean verificaDuplicidad(Class<?> type, String parametro, String valor) {

        List<Object> resultado = getSession().createQuery("SELECT entity FROM " + type.getName() + " entity where entity." + parametro + "=:nombre ").setParameter("nombre", valor).list();

        if (resultado != null && !resultado.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
    

}
