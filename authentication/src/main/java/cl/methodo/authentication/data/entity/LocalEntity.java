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
@Table(name = "tb_local")
public class LocalEntity extends GenericEntity {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(unique = true)
    protected String mSId;

    @ManyToOne(optional = false)
    private CadenaRestauranteEntity cadenaRestaurante;

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

    public CadenaRestauranteEntity getCadenaRestaurante() {
        return cadenaRestaurante;
    }

    public void setCadenaRestaurante(CadenaRestauranteEntity cadenaRestaurante) {
        this.cadenaRestaurante = cadenaRestaurante;
    }

}
