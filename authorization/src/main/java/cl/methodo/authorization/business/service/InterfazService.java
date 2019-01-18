/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.business.service;

import cl.methodo.authorization.business.util.Converter;
import cl.methodo.authorization.client.dto.InterfazDTO;
import cl.methodo.authorization.client.exception.AuthorizationException;
import cl.methodo.authorization.data.entity.InterfazEntity;
import cl.methodo.authorization.data.repository.InterfazRepository;
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
public class InterfazService {

    @Autowired
    InterfazRepository interfazRepository;

    public InterfazDTO create(InterfazDTO interfazDTO) throws AuthorizationException {
        if (interfazDTO == null) {
            throw new AuthorizationException("interfazDTO is null");
        }
        if (interfazDTO.getNombre() == null) {
            throw new AuthorizationException("interfazDTO.getNombre() is null");
        }
        InterfazEntity interfazEntity = new InterfazEntity();
        interfazEntity.setNombre(interfazDTO.getNombre());
        interfazEntity.setDescripcion(interfazDTO.getDescripcion());
        interfazEntity.setEstatus(Estatus.ACTIVO);
        interfazEntity.setFechaHoraCreacion(new Date());
        interfazEntity.setFechaHoraActualizacion(new Date());
        this.interfazRepository.save(interfazEntity);
        return interfazDTO;
    }

    public InterfazDTO edit(String nombre, InterfazDTO interfazDTO) throws AuthorizationException {
        if (interfazDTO == null) {
            throw new AuthorizationException("interfazDTO is null");
        }
        if (nombre == null) {
            throw new AuthorizationException("nombre is null");
        }
        InterfazEntity interfazEntity = this.interfazRepository.findByNombre(nombre);
        if (interfazEntity == null) {
            throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
        }
        if (interfazDTO.getNombre() != null) {
            interfazEntity.setNombre(interfazDTO.getNombre());
        }
        if (interfazDTO.getDescripcion() != null) {
            interfazEntity.setDescripcion(interfazDTO.getDescripcion());
        }
        interfazEntity.setFechaHoraActualizacion(new Date());
        return Converter.interfazConverter(this.interfazRepository.save(interfazEntity));
    }

    public List<InterfazDTO> findAll() throws AuthorizationException {
        List<InterfazEntity> interfazEntitys = this.interfazRepository.findAll();
        if (interfazEntitys == null) {
            throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
        }
        return Converter.interfazConverter(interfazEntitys);
    }
}
