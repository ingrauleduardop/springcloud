/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.client.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rperez
 */
public class CadenaRestauranteDTO implements Serializable{
    
    private String idMSCadenaRestaurante;
    private String nombre;
    private String profileImage;
    private List<LocalDTO> localDTOs;

    public String getIdMSCadenaRestaurante() {
        return idMSCadenaRestaurante;
    }

    public void setIdMSCadenaRestaurante(String idMSCadenaRestaurante) {
        this.idMSCadenaRestaurante = idMSCadenaRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public List<LocalDTO> getLocalDTOs() {
        if(localDTOs==null){
            localDTOs= new ArrayList();
        }
        return localDTOs;
    }

    public void setLocalDTOs(List<LocalDTO> localDTOs) {
        this.localDTOs = localDTOs;
    }
    
}
