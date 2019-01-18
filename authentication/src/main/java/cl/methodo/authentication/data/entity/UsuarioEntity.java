/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.data.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rperez
 */
@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity extends GenericEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_hora_vencimiento", nullable = false, updatable = false)
    protected Date fechaHoraVencimiento;
    
    @OneToMany
    private List<CadenaRestauranteEntity> cadenaRestaurantes;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaHoraVencimiento() {
        return fechaHoraVencimiento;
    }

    public void setFechaHoraVencimiento(Date fechaHoraVencimiento) {
        this.fechaHoraVencimiento = fechaHoraVencimiento;
    }
    
    public List<CadenaRestauranteEntity> getCadenaRestaurantes() {
        return cadenaRestaurantes;
    }

    public void setCadenaRestaurantes(List<CadenaRestauranteEntity> cadenaRestaurantes) {
        this.cadenaRestaurantes = cadenaRestaurantes;
    }

}
