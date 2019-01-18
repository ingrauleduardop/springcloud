/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.client.dto;

import java.io.Serializable;

/**
 *
 * @author rperez
 */
public class PrivilegioDTO implements Serializable{
    
    private String nombre;
    private String idMSRol;
    private String username;
    private CadenaRestauranteDTO cadenaRestauranteDTO;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdMSRol() {
        return idMSRol;
    }

    public void setIdMSRol(String idMSRol) {
        this.idMSRol = idMSRol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CadenaRestauranteDTO getCadenaRestauranteDTO() {
        return cadenaRestauranteDTO;
    }

    public void setCadenaRestauranteDTO(CadenaRestauranteDTO cadenaRestauranteDTO) {
        this.cadenaRestauranteDTO = cadenaRestauranteDTO;
    }

}
