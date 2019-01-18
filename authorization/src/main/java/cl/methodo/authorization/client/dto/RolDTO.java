/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.client.dto;

import java.io.Serializable;

/**
 *
 * @author rperez
 */
public class RolDTO implements Serializable{
    
    private String nombre;
    private String descripcion;
    private String msidRol;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMsidRol() {
        return msidRol;
    }

    public void setMsidRol(String msidRol) {
        this.msidRol = msidRol;
    }

}
