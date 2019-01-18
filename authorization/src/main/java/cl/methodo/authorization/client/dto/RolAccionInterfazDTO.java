/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.client.dto;

import cl.methodo.authorization.data.entity.RolAccionInterfazEntity;

/**
 *
 * @author rperez
 */
public class RolAccionInterfazDTO extends RolAccionInterfazEntity{
    
    private RolDTO rolDTO;

    private AccionDTO accionDTO;

    private InterfazDTO interfazDTO;

    public RolDTO getRolDTO() {
        return rolDTO;
    }

    public void setRolDTO(RolDTO rolDTO) {
        this.rolDTO = rolDTO;
    }

    public AccionDTO getAccionDTO() {
        return accionDTO;
    }

    public void setAccionDTO(AccionDTO accionDTO) {
        this.accionDTO = accionDTO;
    }

    public InterfazDTO getInterfazDTO() {
        return interfazDTO;
    }

    public void setInterfazDTO(InterfazDTO interfazDTO) {
        this.interfazDTO = interfazDTO;
    }
    
}
