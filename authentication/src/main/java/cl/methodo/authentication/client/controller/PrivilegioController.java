/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authentication.client.controller;

import cl.methodo.authentication.business.service.PrivilegioService;
import cl.methodo.authentication.business.util.MessageUtil;
import cl.methodo.authentication.client.dto.ApiResponseDTO;
import cl.methodo.authentication.client.dto.PrivilegioDTO;
import cl.methodo.authentication.client.exception.AuthenticationException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping(value = "/v1/privilegios")
public class PrivilegioController {

    @Autowired
    private PrivilegioService privilegioService;

    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> create(@RequestBody PrivilegioDTO privilegioDTO) {
        try {
            this.privilegioService.create(privilegioDTO);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje("CREATED");
            MessageUtil.message("Privilegio creado", privilegioDTO.getIdMSRol());
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
    @RequestMapping(value = "usuario/{username}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> deleteByUsuarioUsername(@PathVariable("username") String username) {
        try {
            this.privilegioService.deleteByUsuarioUsername(username);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje("DELETED");
            MessageUtil.message("Privilegios borrados del usuario", username);
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
    @RequestMapping(value = "usuario/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> findAllByUsuarioUsername(@PathVariable("username") String username) {
        try {
            List<PrivilegioDTO> privilegioDTOs = this.privilegioService.findAllByUsername(username);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje("FOUND");
            apiResponseDTO.setData(privilegioDTOs);
            return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
        } catch (AuthenticationException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
