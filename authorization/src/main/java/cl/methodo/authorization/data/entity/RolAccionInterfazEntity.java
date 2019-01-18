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
@Table(name = "tb_rol_accion_interfaz")
public class RolAccionInterfazEntity implements Serializable{

    @Id
    @GeneratedValue
    protected Integer id;
    
    @Column
    protected Estatus estatus;

    @ManyToOne(optional = false)
    private RolInterfazEntity rolInterfaz;

    @ManyToOne(optional = false)
    private AccionEntity accion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }
    
    public RolInterfazEntity getRolInterfaz() {
        return rolInterfaz;
    }

    public void setRolInterfaz(RolInterfazEntity rolInterfaz) {
        this.rolInterfaz = rolInterfaz;
    }

    public AccionEntity getAccion() {
        return accion;
    }

    public void setAccion(AccionEntity accion) {
        this.accion = accion;
    }
    
}
