/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.business.service;

import cl.methodo.authentication.data.entity.UsuarioEntity;
import cl.methodo.authentication.data.repository.UsuarioRepository;
import cl.methodo.authentication.security.UserPrincipal;
import cl.methodo_commons.enums.Errors;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author rperez
 */
@Service
public class BaseUserDetailsService implements UserDetailsService{
    
    private final UsuarioRepository usuarioRepository;

    public BaseUserDetailsService(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findByUsername(username);
        if(null==usuarioEntity){
            throw new UsernameNotFoundException(Errors.USER_NOT_FOUND.name() + " " + username);
        }
        return new UserPrincipal(usuarioEntity);
    }

}
