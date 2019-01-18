/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.data.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rperez
 */
@Entity
@Table(name = "tb_cadena_restaurante")
public class CadenaRestauranteEntity extends GenericEntity {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column
    protected String profileImage;
    
    @Column(unique = true)
    protected String mSId;
    
    @OneToMany(mappedBy = "cadenaRestaurante", cascade = CascadeType.ALL)
    private List<LocalEntity> locales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getMSId() {
        return mSId;
    }

    public void setMSId(String MSId) {
        this.mSId = MSId;
    }


    public List<LocalEntity> getLocales() {
        return locales;
    }

    public void setLocales(List<LocalEntity> locales) {
        this.locales = locales;
    }

}
