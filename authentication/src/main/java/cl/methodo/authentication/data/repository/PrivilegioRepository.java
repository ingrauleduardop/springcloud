/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.data.repository;

import cl.methodo.authentication.data.entity.PrivilegioEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rperez
 */
@Repository
public interface PrivilegioRepository extends GenericRepository<PrivilegioEntity, Integer> {

    PrivilegioEntity findByMSId(String idMS);
    
    PrivilegioEntity findByUsuarioEntityUsernameAndLocalEntityId(String usuarioEntityUsername, Integer localEntityId);
    
    void deleteByUsuarioEntityUsername(String username);
    
    List<PrivilegioEntity> findByUsuarioEntityUsername(String username);
}
