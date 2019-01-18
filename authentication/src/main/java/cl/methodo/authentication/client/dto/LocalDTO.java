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
public class LocalDTO implements Serializable{
    
    private String idMSLocal;
    private String nombre;

    public String getIdMSLocal() {
        return idMSLocal;
    }

    public void setIdMSLocal(String idMSLocal) {
        this.idMSLocal = idMSLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
