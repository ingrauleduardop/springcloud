/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.data.entity;

import cl.methodo_commons.enums.Estatus;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rperez
 */
@Entity
@Table(name = "tb_rol_interfaz")
public class RolInterfazEntity implements Serializable{
    
    @Id
    @GeneratedValue
    protected Integer id;
    
    @Column
    protected Estatus estatus;

    @ManyToOne(optional = false)
    private RolEntity rol;

    @ManyToOne(optional = false)
    private InterfazEntity interfaz;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public void setInterfaz(InterfazEntity interfaz) {
        this.interfaz = interfaz;
    }
    
}
