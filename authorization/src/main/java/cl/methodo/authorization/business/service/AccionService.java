/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.business.service;

import cl.methodo.authorization.business.util.Converter;
import cl.methodo.authorization.client.dto.AccionDTO;
import cl.methodo.authorization.client.exception.AuthorizationException;
import cl.methodo.authorization.data.entity.AccionEntity;
import cl.methodo.authorization.data.repository.AccionRepository;
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
public class AccionService {

    @Autowired
    private AccionRepository accionRepository;

    public AccionDTO create(AccionDTO accionDTO) throws AuthorizationException {
        if (accionDTO == null) {
            throw new AuthorizationException("accionDTO is null");
        }
        if (accionDTO.getNombre() == null) {
            throw new AuthorizationException("accionDTO.getNombre() is null");
        }
        if (accionDTO.getColor() == null) {
            throw new AuthorizationException("accionDTO.getColor() is null");
        }
        AccionEntity accionEntity = new AccionEntity();
        accionEntity.setNombre(accionDTO.getNombre());
        accionEntity.setColor(accionDTO.getColor());
        accionEntity.setDescripcion(accionDTO.getDescripcion());
        accionEntity.setIcon(accionDTO.getIcon());
        accionEntity.setEstatus(Estatus.ACTIVO);
        accionEntity.setFechaHoraCreacion(new Date());
        accionEntity.setFechaHoraActualizacion(new Date());
        this.accionRepository.save(accionEntity);
        return accionDTO;
    }

    public AccionDTO edit(String nombre, AccionDTO accionDTO) throws AuthorizationException {
        if (accionDTO == null) {
            throw new AuthorizationException("accionDTO is null");
        }
        if (nombre == null) {
            throw new AuthorizationException("nombre is null");
        }
        AccionEntity accionEntity = this.accionRepository.findByNombre(nombre);
        if (accionEntity == null) {
            throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
        }
        if (accionDTO.getNombre() != null) {
            accionEntity.setNombre(accionDTO.getNombre());
        }
        if (accionDTO.getColor() != null) {
            accionEntity.setColor(accionDTO.getColor());
        }
        if (accionDTO.getDescripcion() != null) {
            accionEntity.setDescripcion(accionDTO.getDescripcion());
        }
        if (accionDTO.getIcon() != null) {
            accionEntity.setIcon(accionDTO.getIcon());
        }
        accionEntity.setFechaHoraActualizacion(new Date());
        this.accionRepository.save(accionEntity);
        return accionDTO;
    }

    public List<AccionDTO> findAll() throws AuthorizationException {
        List<AccionEntity> accionEntitys = this.accionRepository.findAll();
        if (accionEntitys == null) {
            throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
        }
        return Converter.accionConverter(accionEntitys);
    }

}
