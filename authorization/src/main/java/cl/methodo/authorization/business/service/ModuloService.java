/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.business.service;

import cl.methodo.authorization.business.util.Converter;
import cl.methodo.authorization.client.dto.ModuloDTO;
import cl.methodo.authorization.client.exception.AuthorizationException;
import cl.methodo.authorization.data.entity.ModuloEntity;
import cl.methodo.authorization.data.repository.ModuloRepository;
import cl.methodo_commons.MessageUtil;
import cl.methodo_commons.enums.Errors;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rperez
 */
@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    public ModuloDTO create(ModuloDTO moduloDTO) throws AuthorizationException {
        if (moduloDTO == null) {
            throw new AuthorizationException("moduloDTO is null");
        }
        if (moduloDTO.getNombre() == null) {
            throw new AuthorizationException("moduloDTO.getNombre() is null");
        }
        ModuloEntity moduloEntity = new ModuloEntity(moduloDTO);
        if (moduloDTO.getModuloDTO() != null) {
            if (moduloDTO.getModuloDTO().getNombre() == null) {
                throw new AuthorizationException("moduloDTO.getModuloDTO().getNombre() is null");
            }
            ModuloEntity moduloEntityPadre = this.moduloRepository.findByNombre(moduloDTO.getModuloDTO().getNombre());
            if (moduloEntityPadre == null) {
                MessageUtil.message("La Modulo padre NO EXISTE, se debe crear", moduloDTO.getModuloDTO().getNombre());
                moduloEntityPadre = new ModuloEntity(moduloDTO.getModuloDTO());
                moduloEntityPadre = this.moduloRepository.save(moduloEntityPadre);
            }
            moduloEntity.setModuloPadre(moduloEntityPadre);
        }
        return Converter.moduloConverter(this.moduloRepository.save(moduloEntity));

    }

    public ModuloDTO edit(String nombre, ModuloDTO moduloDTO) throws AuthorizationException {
        if (moduloDTO == null) {
            throw new AuthorizationException("moduloDTO is null");
        }
        if (nombre == null) {
            throw new AuthorizationException("nombre is null");
        }
        ModuloEntity moduloEntity = this.moduloRepository.findByNombre(nombre);
        if (moduloEntity == null) {
            throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
        }
        if (moduloDTO.getDescripcion() != null) {
            moduloEntity.setDescripcion(moduloDTO.getDescripcion());
        }
        if (moduloDTO.getNombre() != null) {
            moduloEntity.setNombre(moduloDTO.getNombre());
        }
        if (moduloDTO.getModuloDTO() != null) {
            if (moduloDTO.getModuloDTO().getNombre() == null) {
                throw new AuthorizationException("moduloDTO.getModuloDTO().getNombre() is null");
            }
            ModuloEntity moduloEntityPadre = this.moduloRepository.findByNombre(moduloDTO.getModuloDTO().getNombre());
            if (moduloEntityPadre == null) {
                throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
            }
            moduloEntity.setModuloPadre(moduloEntityPadre);
        }
        moduloEntity.setFechaHoraActualizacion(new Date());
        return Converter.moduloConverter(this.moduloRepository.save(moduloEntity));
    }

    public List<ModuloDTO> findAll() throws AuthorizationException{
        List<ModuloEntity> moduloEntitys = this.moduloRepository.findAll();
        if (moduloEntitys == null) {
            throw new AuthorizationException(Errors.ENTITY_NOT_FOUND);
        }
        return Converter.moduloConverter(moduloEntitys);
    }
}
