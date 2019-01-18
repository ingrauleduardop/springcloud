/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.business.service;

import cl.methodo_commons.enums.Estatus;
import cl.methodo.authentication.business.util.Converter;
import cl.methodo.authentication.business.util.MessageUtil;
import cl.methodo.authentication.client.dto.CadenaRestauranteDTO;
import cl.methodo.authentication.client.dto.LocalDTO;
import cl.methodo.authentication.client.dto.UsuarioDTO;
import cl.methodo.authentication.client.exception.AuthenticationException;
import cl.methodo.authentication.data.entity.CadenaRestauranteEntity;
import cl.methodo.authentication.data.entity.LocalEntity;
import cl.methodo.authentication.data.entity.UsuarioEntity;
import cl.methodo.authentication.data.repository.CadenaRestauranteRepository;
import cl.methodo.authentication.data.repository.LocalRepository;
import cl.methodo.authentication.data.repository.UsuarioRepository;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadenaRestauranteRepository cadenaRestauranteRepository;

    @Autowired
    private LocalRepository localRepository;

    public UsuarioDTO create(UsuarioDTO usuarioDTO) throws AuthenticationException {
        if (usuarioDTO == null) {
            throw new AuthenticationException("usuarioDTO is null");
        }
        if (usuarioDTO.getNombre() == null) {
            throw new AuthenticationException("usuarioDTO.getNombre() is null");
        }
        if (usuarioDTO.getPassword() == null) {
            throw new AuthenticationException("usuarioDTO.getPassword() is null");
        }
        if (usuarioDTO.getUsername() == null) {
            throw new AuthenticationException("usuarioDTO.getUsername() is null");
        }
        if (usuarioDTO.getCadenaRestauranteDTOs().isEmpty()) {
            throw new AuthenticationException("usuarioDTO.getCadenaRestauranteDTOs() is empty");
        }
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre(usuarioDTO.getNombre());
        usuarioEntity.setPassword(usuarioDTO.getPassword());
        usuarioEntity.setUsername(usuarioDTO.getUsername());
        usuarioEntity.setEstatus(Estatus.ACTIVO);
        usuarioEntity.setFechaHoraCreacion(new Date());
        usuarioEntity.setFechaHoraActualizacion(new Date());

        List<CadenaRestauranteEntity> cadenaRestauranteEntitys = new ArrayList();
        for (CadenaRestauranteDTO cadenaRestauranteDTO : usuarioDTO.getCadenaRestauranteDTOs()) {
            if (cadenaRestauranteDTO == null) {
                throw new AuthenticationException("cadenaRestauranteDTO is null");
            }
            if (cadenaRestauranteDTO.getIdMSCadenaRestaurante() == null) {
                throw new AuthenticationException("cadenaRestauranteDTO.getIdMSCadenaRestaurante() is null");
            }

            //Validate if CadenaRestaurante exits
            CadenaRestauranteEntity cadenaRestauranteEntity = this.cadenaRestauranteRepository.findByMSId(cadenaRestauranteDTO.getIdMSCadenaRestaurante());
            if (cadenaRestauranteEntity == null) {
                MessageUtil.message("La Cadena Restaurante NO EXISTE, se debe crear", cadenaRestauranteDTO.getIdMSCadenaRestaurante());
                cadenaRestauranteEntity = new CadenaRestauranteEntity();
                cadenaRestauranteEntity.setMSId(cadenaRestauranteDTO.getIdMSCadenaRestaurante());
                cadenaRestauranteEntity.setNombre(cadenaRestauranteDTO.getNombre());
                cadenaRestauranteEntity.setProfileImage(cadenaRestauranteDTO.getProfileImage());
                cadenaRestauranteEntity.setEstatus(Estatus.ACTIVO);
                cadenaRestauranteEntity.setFechaHoraCreacion(new Date());
                cadenaRestauranteEntity.setFechaHoraActualizacion(new Date());
                cadenaRestauranteEntity = this.cadenaRestauranteRepository.save(cadenaRestauranteEntity);
            }
            cadenaRestauranteEntitys.add(cadenaRestauranteEntity);

            if (cadenaRestauranteDTO.getLocalDTOs().isEmpty()) {
                throw new AuthenticationException("usuarioDTO.getCadenaRestauranteDTO().getLocalDTOs() is empty");
            }
            List<LocalEntity> localEntitys = new ArrayList();
            for (LocalDTO localDTO : cadenaRestauranteDTO.getLocalDTOs()) {
                if (localDTO.getIdMSLocal() == null) {
                    throw new AuthenticationException("usuarioDTO.getCadenaRestauranteDTO().getLocalDTOs().getIdMSLocal() is null");
                }
                LocalEntity localEntity = this.localRepository.findByMSId(localDTO.getIdMSLocal());
                if (localEntity == null) {
                    MessageUtil.message("El local NO EXISTE, se debe crear", localDTO.getIdMSLocal());
                    localEntity = new LocalEntity();
                    localEntity.setmSId(localDTO.getIdMSLocal());
                    localEntity.setNombre(localDTO.getNombre());
                    localEntity.setEstatus(Estatus.ACTIVO);
                    localEntity.setFechaHoraCreacion(new Date());
                    localEntity.setFechaHoraActualizacion(new Date());
                    localEntity.setCadenaRestaurante(cadenaRestauranteEntity);
                    localEntitys.add(localEntity);
                }
            }
            if (!localEntitys.isEmpty()) {
                this.localRepository.saveAll(localEntitys);
            }
        }
        usuarioEntity.setCadenaRestaurantes(cadenaRestauranteEntitys);
        usuarioRepository.save(usuarioEntity);
        return usuarioDTO;
    }

    public UsuarioDTO findOneByUsername(String username) throws AuthenticationException {
        if (username == null) {
            throw new AuthenticationException("username is null");
        }
        UsuarioEntity usuarioEntity = this.usuarioRepository.findByUsername(username);
        if (usuarioEntity == null) {
            throw new AuthenticationException("ENTITY NOUT FOUND");
        }
        return Converter.usuarioConverter(usuarioEntity);
    }

    public UsuarioDTO edit(String username, UsuarioDTO usuarioDTO) throws AuthenticationException {
        if (username == null) {
            throw new AuthenticationException("username is null");
        }
        if (usuarioDTO == null) {
            throw new AuthenticationException("usuarioDTO is null");
        }
        UsuarioEntity usuarioEntity = this.usuarioRepository.findByUsername(username);
        if (usuarioEntity == null) {
            throw new AuthenticationException("ENTITY NOT FOUND");
        }
        if (usuarioDTO.getNombre() != null) {
            usuarioEntity.setNombre(usuarioDTO.getNombre());
        }
        if (usuarioDTO.getPassword()!= null) {
            usuarioEntity.setPassword(usuarioDTO.getPassword());
        }
        if (usuarioDTO.getUsername()!= null) {
            usuarioEntity.setUsername(usuarioDTO.getUsername());
        }
        usuarioEntity.setFechaHoraActualizacion(new Date());
        usuarioRepository.save(usuarioEntity);
        return Converter.usuarioConverter(usuarioEntity);
    }
    
    public void delete(String username) throws AuthenticationException {
        if (username == null) {
            throw new AuthenticationException("username is null");
        }
        this.usuarioRepository.deleteByUsername(username);
    }
    
    public List<UsuarioDTO> findAll() throws AuthenticationException {
        List<UsuarioEntity> usuarioEntitys = this.usuarioRepository.findAll();
        if (usuarioEntitys == null) {
            throw new AuthenticationException("ENTITY NOUT FOUND");
        }
        return Converter.usuarioConverter(usuarioEntitys);
    }

}
