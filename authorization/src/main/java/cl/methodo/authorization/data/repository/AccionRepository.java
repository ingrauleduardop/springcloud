/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.data.repository;

import cl.methodo.authorization.data.entity.AccionEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rperez
 */
@Repository
public interface AccionRepository extends GenericRepository<AccionEntity, Integer>{
    
}
