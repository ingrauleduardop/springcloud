/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.data.entity;

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
@Table(name = "tb_privilegio")
public class PrivilegioEntity  extends GenericEntity {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    private String mSId;
    
    @ManyToOne(optional = false)
    private LocalEntity localEntity;
    
    @ManyToOne(optional = false)
    private UsuarioEntity usuarioEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getmSId() {
        return mSId;
    }

    public void setmSId(String mSId) {
        this.mSId = mSId;
    }
  
    public LocalEntity getLocalEntity() {
        return localEntity;
    }

    public void setLocalEntity(LocalEntity localEntity) {
        this.localEntity = localEntity;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
    
}
