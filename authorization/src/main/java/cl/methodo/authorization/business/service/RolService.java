/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.business.service;

import cl.methodo.authorization.business.util.Converter;
import cl.methodo.authorization.client.dto.RolDTO;
import cl.methodo.authorization.client.exception.AuthorizationException;
import cl.methodo.authorization.data.entity.RolEntity;
import cl.methodo.authorization.data.repository.RolRepository;
import cl.methodo_commons.MSIdGenerator;
import cl.methodo_commons.enums.Errors;
import cl.methodo_commons.enums.Estatus;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rperez
 */
@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;
    
  public RolDTO create(RolDTO rolDTO) throws AuthorizationException {
        if (rolDTO == null) {
            throw new AuthorizationException("rolDTO is null");
        }
        if (rolDTO.getNombre() == null) {
            throw new AuthorizationException("rolDTO.getNombre() is null");
        }
        RolEntity rolEntity = new RolEntity();
        rolEntity.setNombre(rolDTO.getNombre());
        rolEntity.setDescripcion(rolDTO.getDescripcion());
        rolEntity.setEstatus(Estatus.ACTIVO);
        rolEntity.setFechaHoraCreacion(new Date());
        rolEntity.setFechaHoraActualizacion(new Date());
        rolEntity.setMsidRol(MSIdGenerator.idGenerator(RolEntity.class));
        this.rolRepository.save(rolEntity);
        return rolDTO;
    }

    public RolDTO edit(String nombre, RolDTO rolDTO) throws AuthorizationException {
        if (rolDTO == null) {
            throw new AuthorizationException("rolDTO is null");
        }
        if (nombre == null) {
            throw new AuthorizationException("nombre is null");
        }
        RolEntity rolEntity = this.rolRepository.findByNombre(nombre);
        if (rolEntity == null) {
            throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
        }
        if (rolDTO.getNombre() != null) {
            rolEntity.setNombre(rolDTO.getNombre());
        }
        if (rolDTO.getDescripcion() != null) {
            rolEntity.setDescripcion(rolDTO.getDescripcion());
        }
        rolEntity.setFechaHoraActualizacion(new Date());
        return Converter.rolConverter(this.rolRepository.save(rolEntity));
    }

    public List<RolDTO> findAll() throws AuthorizationException {
        List<RolEntity> rolEntitys = this.rolRepository.findAll();
        if (rolEntitys == null) {
            throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
        }
        return Converter.rolConverter(rolEntitys);
    }
}
