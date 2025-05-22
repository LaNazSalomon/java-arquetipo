/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.ca.base.services.impl;



import com.mx.ca.base.repositorys.GenericoRepository;
import com.mx.ca.base.services.GenericoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jbecerril
 */
@Service(value = "genericoService")
public class GenericoServiceImpl implements GenericoService {

    @Autowired
    GenericoRepository genericoRepository;

    @Override
    public boolean guardar(Object cliente) {
        return genericoRepository.guardar(cliente);
    }

    @Override
    public boolean update(Object cliente) {
        return genericoRepository.update(cliente);
    }

    @Override
    public boolean delete(Object cliente) {
        return genericoRepository.delete(cliente);
    }

    @Override
    public <T> T findByID(Class<T> type, Long id) {
        return genericoRepository.findByID(type, id);
    }

    @Override
    public <T> T findByID(Class<T> type, int id) {
        return genericoRepository.findByID(type, id);
    }

    @Override
    public <T> List<T> findAll(Class<T> type, boolean activos) {
        return genericoRepository.findAll(type, activos);
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        return genericoRepository.findAll(type);
    }

    @Override
    public boolean verificaDuplicidad(Class<?> type, String parametro, String valor) {

        return genericoRepository.verificaDuplicidad(type, parametro, valor);
    }

}
