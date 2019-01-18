/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.data.entity;

import cl.methodo_commons.enums.Estatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 *
 * @author rperez
 */
@MappedSuperclass
public class GenericEntity implements Serializable {

    @Column
    protected String nombre;

    @Column(length=255)
    protected String descripcion;

    @Version
    @Column
    protected Integer version;

    @Column
    protected Estatus estatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_creacion", nullable = false, updatable = false)
    @CreatedDate
    protected Date fechaHoraCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_actualizacion", nullable = false)
    @LastModifiedDate
    protected Date fechaHoraActualizacion;

    public GenericEntity() {
    }

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

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public Date getFechaHoraActualizacion() {
        return fechaHoraActualizacion;
    }

    public void setFechaHoraActualizacion(Date fechaHoraActualizacion) {
        this.fechaHoraActualizacion = fechaHoraActualizacion;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}

