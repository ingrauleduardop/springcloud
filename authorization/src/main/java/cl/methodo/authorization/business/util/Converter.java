/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.business.util;

import cl.methodo.authorization.client.dto.AccionDTO;
import cl.methodo.authorization.client.dto.InterfazDTO;
import cl.methodo.authorization.client.dto.ModuloDTO;
import cl.methodo.authorization.client.dto.RolDTO;
import cl.methodo.authorization.data.entity.AccionEntity;
import cl.methodo.authorization.data.entity.InterfazEntity;
import cl.methodo.authorization.data.entity.ModuloEntity;
import cl.methodo.authorization.data.entity.RolEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rperez
 */
public class Converter {

    public static List<AccionDTO> accionConverter(List<AccionEntity> accionEntitys) {
        List<AccionDTO> accionDTOs = new ArrayList();
        accionEntitys.forEach((accionEntity) -> {
            accionDTOs.add(accionConverter(accionEntity));
        });
        return accionDTOs;
    }

    public static AccionDTO accionConverter(AccionEntity accionEntity) {
        AccionDTO accionDTO = new AccionDTO();
        accionDTO.setColor(accionEntity.getColor());
        accionDTO.setDescripcion(accionEntity.getDescripcion());
        accionDTO.setNombre(accionEntity.getNombre());
        accionDTO.setIcon(accionEntity.getIcon());
        return accionDTO;
    }

    public static List<ModuloDTO> moduloConverter(List<ModuloEntity> moduloEntitys) {
        List<ModuloDTO> moduloDTOs = new ArrayList();
        moduloEntitys.forEach((moduloEntity) -> {
            moduloDTOs.add(moduloConverter(moduloEntity));
        });
        return moduloDTOs;
    }

    public static ModuloDTO moduloConverter(ModuloEntity moduloEntity) {
        ModuloDTO moduloDTO = new ModuloDTO();
        moduloDTO.setDescripcion(moduloEntity.getDescripcion());
        moduloDTO.setNombre(moduloEntity.getNombre());
        if (moduloEntity.getModuloPadre() != null) {
            moduloDTO.setModuloDTO(moduloConverter(moduloEntity.getModuloPadre()));
        }
        return moduloDTO;
    }

    public static List<InterfazDTO> interfazConverter(List<InterfazEntity> interfazEntitys) {
        List<InterfazDTO> interfazDTOs = new ArrayList();
        interfazEntitys.forEach((interfazEntity) -> {
            interfazDTOs.add(interfazConverter(interfazEntity));
        });
        return interfazDTOs;
    }

    public static InterfazDTO interfazConverter(InterfazEntity interfazEntity) {
        InterfazDTO interfazDTO = new InterfazDTO();
        interfazDTO.setNombre(interfazEntity.getNombre());
        interfazDTO.setDescripcion(interfazEntity.getDescripcion());
        interfazDTO.setPath(interfazEntity.getPath());
        interfazDTO.setIcono(interfazEntity.getIcon());
        return interfazDTO;
    }

    public static List<RolDTO> rolConverter(List<RolEntity> rolEntitys) {
        List<RolDTO> rolDTOs = new ArrayList();
        rolEntitys.forEach((rolEntity) -> {
            rolDTOs.add(rolConverter(rolEntity));
        });
        return rolDTOs;
    }

    public static RolDTO rolConverter(RolEntity rolEntity) {
        RolDTO rolDTO = new RolDTO();

        return rolDTO;
    }

}
