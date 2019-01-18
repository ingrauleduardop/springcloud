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
public class UsuarioDTO implements Serializable {

    private String nombre;
    private String username;
    private String password;
    private List<CadenaRestauranteDTO> cadenaRestauranteDTOs;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CadenaRestauranteDTO> getCadenaRestauranteDTOs() {
        if (cadenaRestauranteDTOs == null) {
            cadenaRestauranteDTOs = new ArrayList();
        }
        return cadenaRestauranteDTOs;
    }

    public void setCadenaRestauranteDTOs(List<CadenaRestauranteDTO> cadenaRestauranteDTOs) {
        this.cadenaRestauranteDTOs = cadenaRestauranteDTOs;
    }

}
