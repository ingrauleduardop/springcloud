/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author rperez
 */
@Entity
@Table(name = "tb_rol")
public class RolEntity extends GenericEntity {

    @Id
    @GeneratedValue
    protected Integer id;

    @Column(length = 255)
    protected String icon;
    
    @Column(unique = true, updatable = false)
    protected String msidRol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMsidRol() {
        return msidRol;
    }

    public void setMsidRol(String msidRol) {
        this.msidRol = msidRol;
    }

}
