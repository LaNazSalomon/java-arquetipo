/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.base.repositorys;

import com.mx.ca.base.models.CatUsuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jbecerril
 */
@Repository("catUsuariosRepository")
public interface CatUsuariosRepository extends CrudRepository<CatUsuarios, Long> {

    @Query("select usu from CatUsuarios usu where usu.username=:user and usu.password=:pass")
    @Transactional
    CatUsuarios login(@Param("user") String user, @Param("pass") String pass);
    
    
}
