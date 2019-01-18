/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.data.entity;

import cl.methodo.authorization.client.dto.ModuloDTO;
import cl.methodo_commons.enums.Estatus;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author rperez
 */
@Entity
@Table(name = "tb_modulo")
public class ModuloEntity extends GenericEntity{
    
    @Id
    @GeneratedValue
    protected Integer id;

    @Column(length = 255)
    protected String icon;
    
    @ManyToOne(optional = true)
    private ModuloEntity moduloPadre;

    @OneToMany(mappedBy = "modulo")
    private List<InterfazEntity> interfazs;

    public ModuloEntity() {
    }

    public ModuloEntity(ModuloDTO moduloDTO) {
        super.setNombre(moduloDTO.getNombre());
        super.setDescripcion(moduloDTO.getDescripcion());
        super.setEstatus(Estatus.ACTIVO);
        super.setFechaHoraCreacion(new Date());
        super.setFechaHoraActualizacion(new Date());
    }
    
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

    public ModuloEntity getModuloPadre() {
        return moduloPadre;
    }

    public void setModuloPadre(ModuloEntity moduloPadre) {
        this.moduloPadre = moduloPadre;
    }

    public List<InterfazEntity> getInterfazs() {
        return interfazs;
    }

    public void setInterfazs(List<InterfazEntity> interfazs) {
        this.interfazs = interfazs;
    }
    
}
