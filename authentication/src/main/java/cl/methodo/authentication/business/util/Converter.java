/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.business.util;

import cl.methodo.authentication.client.dto.CadenaRestauranteDTO;
import cl.methodo.authentication.client.dto.LocalDTO;
import cl.methodo.authentication.client.dto.PrivilegioDTO;
import cl.methodo.authentication.client.dto.UsuarioDTO;
import cl.methodo.authentication.data.entity.CadenaRestauranteEntity;
import cl.methodo.authentication.data.entity.LocalEntity;
import cl.methodo.authentication.data.entity.PrivilegioEntity;
import cl.methodo.authentication.data.entity.UsuarioEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rperez
 */
public class Converter {

    public static UsuarioDTO usuarioConverter(UsuarioEntity usuarioEntity) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre(usuarioEntity.getNombre());
        usuarioDTO.setPassword(usuarioEntity.getPassword());
        usuarioDTO.setUsername(usuarioEntity.getUsername());
        if (usuarioEntity.getCadenaRestaurantes() != null) {
            usuarioDTO.setCadenaRestauranteDTOs(cadenaRestauranteConverter(usuarioEntity.getCadenaRestaurantes()));
        }
        return usuarioDTO;
    }

    public static List<UsuarioDTO> usuarioConverter(List<UsuarioEntity> usuarioEntitys) {
        List<UsuarioDTO> usuarioDTOs = new ArrayList();
        for (UsuarioEntity usuarioEntity : usuarioEntitys) {
            usuarioDTOs.add(usuarioConverter(usuarioEntity));
        }
        return usuarioDTOs;
    }

    public static CadenaRestauranteDTO cadenaRestauranteConverter(CadenaRestauranteEntity cadenaRestauranteEntity) {
        CadenaRestauranteDTO cadenaRestauranteDTO = new CadenaRestauranteDTO();
        cadenaRestauranteDTO.setNombre(cadenaRestauranteEntity.getNombre());
        cadenaRestauranteDTO.setIdMSCadenaRestaurante(cadenaRestauranteEntity.getMSId());
        cadenaRestauranteDTO.setProfileImage(cadenaRestauranteEntity.getProfileImage());
        if (cadenaRestauranteEntity.getLocales() != null) {
            cadenaRestauranteDTO.setLocalDTOs(localConverter(cadenaRestauranteEntity.getLocales()));
        }
        return cadenaRestauranteDTO;
    }

    public static List<CadenaRestauranteDTO> cadenaRestauranteConverter(List<CadenaRestauranteEntity> cadenaRestauranteEntitys) {
        List<CadenaRestauranteDTO> cadenaRestauranteDTOs = new ArrayList();
        for (CadenaRestauranteEntity cadenaRestauranteEntity : cadenaRestauranteEntitys) {
            cadenaRestauranteDTOs.add(cadenaRestauranteConverter(cadenaRestauranteEntity));
        }
        return cadenaRestauranteDTOs;
    }

    public static LocalDTO localConverter(LocalEntity localEntity) {
        LocalDTO localDTO = new LocalDTO();
        localDTO.setNombre(localEntity.getNombre());
        localDTO.setIdMSLocal(localEntity.getmSId());
        return localDTO;
    }

    public static List<LocalDTO> localConverter(List<LocalEntity> localEntitys) {
        List<LocalDTO> localDTOs = new ArrayList();
        for (LocalEntity localEntity : localEntitys) {
            localDTOs.add(localConverter(localEntity));
        }
        return localDTOs;
    }

    public static PrivilegioDTO privilegioConverter(PrivilegioEntity privilegioEntity) {
        PrivilegioDTO privilegioDTO = new PrivilegioDTO();
        privilegioDTO.setIdMSRol(privilegioEntity.getmSId());
        if (privilegioEntity.getUsuarioEntity() != null) {
            privilegioDTO.setUsername(privilegioEntity.getUsuarioEntity().getUsername());
        }
        if (privilegioEntity.getLocalEntity() != null) {
            if (privilegioEntity.getLocalEntity().getCadenaRestaurante() != null) {
                privilegioDTO.setCadenaRestauranteDTO(cadenaRestauranteConverter(privilegioEntity.getLocalEntity().getCadenaRestaurante()));
            }
        }
        return privilegioDTO;
    }

    public static List<PrivilegioDTO> privilegioConverter(List<PrivilegioEntity> privilegioEntitys) {
        List<PrivilegioDTO> privilegioDTOs = new ArrayList();
        Set<String> rols = new HashSet();
        for (PrivilegioEntity privilegioEntity : privilegioEntitys) {
            if (!rols.contains(privilegioEntity.getmSId())) {
                privilegioDTOs.add(privilegioConverter(privilegioEntity));
                rols.add(privilegioEntity.getmSId());
            }
        }
        return privilegioDTOs;
    }
}
