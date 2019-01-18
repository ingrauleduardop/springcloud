/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.security;

import cl.methodo.authentication.data.entity.UsuarioEntity;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author rperez
 */
public class UserPrincipal implements UserDetails {

    private UsuarioEntity usuarioEntity;

    public UserPrincipal(UsuarioEntity usuarioEntity) {
        super();
        this.usuarioEntity = usuarioEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return this.usuarioEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.usuarioEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.usuarioEntity.getFechaHoraVencimiento().after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
