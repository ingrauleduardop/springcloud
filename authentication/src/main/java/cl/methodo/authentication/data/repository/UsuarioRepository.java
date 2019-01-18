/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.data.repository;

import cl.methodo.authentication.data.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rperez
 */
@Repository
public interface UsuarioRepository extends GenericRepository<UsuarioEntity, Integer> {

    UsuarioEntity findByUsername(String username);
    
    void deleteByUsername(String username);
}
