/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mx.ca.base.repositorys;

import com.mx.ca.base.models.CatMenus;
import com.mx.ca.base.models.CatRoles;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jbecerril
 */
@Repository("catRolesRepository")
public interface CatRolesRepository extends CrudRepository<CatRoles, Long>{
    
    @Query("select r from CatMenus r where r.status=true and r.idMenu not in(:ids) order by r.nombre ASC")
    List<CatMenus> buscarMenusDisponibles(@Param("ids")List<Long> ids);
    
    @Query("select r from CatMenus r where r.status=true order by r.nombre ASC ")
    List<CatMenus> buscarTodosMenusDisponibles();
    
}
