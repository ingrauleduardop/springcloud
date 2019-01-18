/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.business.service;

import cl.methodo.authentication.business.util.Converter;
import cl.methodo.authentication.business.util.MessageUtil;
import cl.methodo.authentication.client.dto.LocalDTO;
import cl.methodo.authentication.client.dto.PrivilegioDTO;
import cl.methodo.authentication.client.exception.AuthenticationException;
import cl.methodo.authentication.data.entity.CadenaRestauranteEntity;
import cl.methodo.authentication.data.entity.LocalEntity;
import cl.methodo.authentication.data.entity.PrivilegioEntity;
import cl.methodo.authentication.data.entity.UsuarioEntity;
import cl.methodo.authentication.data.repository.CadenaRestauranteRepository;
import cl.methodo.authentication.data.repository.LocalRepository;
import cl.methodo.authentication.data.repository.PrivilegioRepository;
import cl.methodo.authentication.data.repository.UsuarioRepository;
import cl.methodo_commons.enums.Errors;
import cl.methodo_commons.enums.Estatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rperez
 */
@Service
public class PrivilegioService {

    @Autowired
    private PrivilegioRepository privilegioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private CadenaRestauranteRepository cadenaRestauranteRepository;

    public void create(PrivilegioDTO privilegioDTO) throws AuthenticationException {
        if (privilegioDTO == null) {
            throw new AuthenticationException("privilegioDTO is null");
        }
        if (privilegioDTO.getIdMSRol() == null) {
            throw new AuthenticationException("privilegioDTO.getIdMSRol() is null");
        }
        if (privilegioDTO.getUsername() == null) {
            throw new AuthenticationException("privilegioDTO.getUsername() is null");
        }
        if (privilegioDTO.getCadenaRestauranteDTO() == null) {
            throw new AuthenticationException("privilegioDTO.getCadenaRestauranteDTO() is null");
        }
        if (privilegioDTO.getCadenaRestauranteDTO().getLocalDTOs().isEmpty()) {
            throw new AuthenticationException("privilegioDTO.getCadenaRestauranteDTO().getLocalDTOs() is empty");
        }
        List<LocalEntity> localEntitys = new ArrayList();
        for (LocalDTO localDTO : privilegioDTO.getCadenaRestauranteDTO().getLocalDTOs()) {
            LocalEntity localEntity = this.localRepository.findByMSId(localDTO.getIdMSLocal());
            if (localEntity == null) {
                if (privilegioDTO.getCadenaRestauranteDTO().getIdMSCadenaRestaurante() == null) {
                    throw new AuthenticationException("privilegioDTO.getCadenaRestauranteDTO().getIdMSCadenaRestaurante() is empty");
                }
                CadenaRestauranteEntity cadenaRestauranteEntity = this.cadenaRestauranteRepository.findByMSId(privilegioDTO.getCadenaRestauranteDTO().getIdMSCadenaRestaurante());
                if (cadenaRestauranteEntity == null) {
                    MessageUtil.message("La Cadena Restaurante NO EXISTE, se debe crear", privilegioDTO.getCadenaRestauranteDTO().getIdMSCadenaRestaurante());
                    cadenaRestauranteEntity = new CadenaRestauranteEntity();
                    cadenaRestauranteEntity.setMSId(privilegioDTO.getCadenaRestauranteDTO().getIdMSCadenaRestaurante());
                    cadenaRestauranteEntity.setNombre(privilegioDTO.getCadenaRestauranteDTO().getNombre());
                    cadenaRestauranteEntity.setProfileImage(privilegioDTO.getCadenaRestauranteDTO().getProfileImage());
                    cadenaRestauranteEntity.setEstatus(Estatus.ACTIVO);
                    cadenaRestauranteEntity.setFechaHoraCreacion(new Date());
                    cadenaRestauranteEntity.setFechaHoraActualizacion(new Date());
                    cadenaRestauranteEntity = this.cadenaRestauranteRepository.save(cadenaRestauranteEntity);
                }
                MessageUtil.message("El local NO EXISTE, se debe crear", localDTO.getIdMSLocal());
                localEntity = new LocalEntity();
                localEntity.setmSId(localDTO.getIdMSLocal());
                localEntity.setNombre(localDTO.getNombre());
                localEntity.setEstatus(Estatus.ACTIVO);
                localEntity.setFechaHoraCreacion(new Date());
                localEntity.setFechaHoraActualizacion(new Date());
                localEntity.setCadenaRestaurante(cadenaRestauranteEntity);
                localEntity = this.localRepository.save(localEntity);
            }
            localEntitys.add(localEntity);
        }
        UsuarioEntity usuarioEntity = this.usuarioRepository.findByUsername(privilegioDTO.getUsername());
        if (usuarioEntity == null) {
            throw new AuthenticationException(Errors.ENTITY_NOT_FOUND);
        }
        for (LocalEntity localEntity : localEntitys) {
            PrivilegioEntity privilegioEntity = this.privilegioRepository.findByUsuarioEntityUsernameAndLocalEntityId(usuarioEntity.getUsername(), localEntity.getId());
            if (privilegioEntity == null) {
                privilegioEntity = new PrivilegioEntity();
                privilegioEntity.setmSId(privilegioDTO.getIdMSRol());
                privilegioEntity.setUsuarioEntity(usuarioEntity);
                privilegioEntity.setLocalEntity(localEntity);
                privilegioEntity.setEstatus(Estatus.ACTIVO);
                privilegioEntity.setFechaHoraCreacion(new Date());
                privilegioEntity.setFechaHoraActualizacion(new Date());
                this.privilegioRepository.save(privilegioEntity);
            } else {
                MessageUtil.message("Privilegio ya existe en local", privilegioDTO.getIdMSRol() + localEntity.getmSId());
            }
        }
    }

    public void deleteByUsuarioUsername(String username) throws AuthenticationException {
        if (username == null) {
            throw new AuthenticationException("username is null");
        }
        this.privilegioRepository.deleteByUsuarioEntityUsername(username);
    }

    public List<PrivilegioDTO> findAllByUsername(String username) throws AuthenticationException {
        if (username == null) {
            throw new AuthenticationException("username is null");
        }
        List<PrivilegioDTO> privilegioDTOs = Converter.privilegioConverter(this.privilegioRepository.findByUsuarioEntityUsername(username));
        return privilegioDTOs;
    }

}
