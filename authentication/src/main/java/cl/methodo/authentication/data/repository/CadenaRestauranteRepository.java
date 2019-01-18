/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.data.repository;

import cl.methodo.authentication.data.entity.CadenaRestauranteEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rperez
 */
@Repository
public interface CadenaRestauranteRepository extends GenericRepository<CadenaRestauranteEntity, Integer>{
     
    CadenaRestauranteEntity findByMSId(String idMS);
}
