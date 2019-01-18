/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.client.controller;

import cl.methodo.authentication.business.service.UsuarioService;
import cl.methodo.authentication.business.util.MessageUtil;
import cl.methodo.authentication.client.dto.ApiResponseDTO;
import cl.methodo.authentication.client.dto.UsuarioDTO;
import cl.methodo.authentication.client.exception.AuthenticationException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rperez
 */
@RestController
@RequestMapping(value = "/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            this.usuarioService.create(usuarioDTO);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje("CREATED");
            MessageUtil.message("Usuario creado", usuarioDTO.getUsername());
            return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
        } catch (AuthenticationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(value = "/{username}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> edit(@PathVariable(value = "username") String username, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO userDTO = this.usuarioService.edit(username, usuarioDTO);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje("EDITED");
            apiResponseDTO.setData(userDTO);
            return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
        } catch (AuthenticationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> delete(@PathVariable(value = "username") String username) {
        try {
            this.usuarioService.delete(username);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje("DELETED");
            return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
        } catch (AuthenticationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> getOne(@PathVariable("username") String username) {
        try {
            UsuarioDTO usuarioDTO = this.usuarioService.findOneByUsername(username);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje("FOUND");
            apiResponseDTO.setData(usuarioDTO);
            return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
        } catch (AuthenticationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> getAll() {
        try {
            List<UsuarioDTO> usuarioDTOs = this.usuarioService.findAll();
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje("FOUND");
            apiResponseDTO.setData(usuarioDTOs);
            return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
        } catch (AuthenticationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
