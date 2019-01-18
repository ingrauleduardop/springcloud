/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.methodo.authorization.client.controller;

import cl.methodo.authorization.business.service.ModuloService;
import cl.methodo.authorization.client.dto.ApiResponseDTO;
import cl.methodo.authorization.client.dto.ModuloDTO;
import cl.methodo.authorization.client.exception.AuthorizationException;
import cl.methodo_commons.enums.OperationMessages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/v1/modulos")
@Api(value = "modulos", tags = "modulos", description = "Esta es una descripcion de modulos")
public class ModuloController {
    
    @Autowired
    private ModuloService moduloService;

    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> create(@RequestBody ModuloDTO moduloDTO) {
        try {
            ModuloDTO modDTO = this.moduloService.create(moduloDTO);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje(OperationMessages.CREATED.name());
            apiResponseDTO.setData(modDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AuthorizationException ex) {
            Logger.getLogger(AccionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> edit(@RequestBody ModuloDTO moduloDTO) {
        try {
            ModuloDTO modDTO = this.moduloService.create(moduloDTO);
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje(OperationMessages.EDITED.name());
            apiResponseDTO.setData(modDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthorizationException ex) {
            Logger.getLogger(AccionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = ApiResponseDTO.class)
        ,
        @ApiResponse(code = 404, message = "Data is not complete")})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<ApiResponseDTO> findAll() {
        try {
            List<ModuloDTO> moduloDTOs = this.moduloService.findAll();
            ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
            apiResponseDTO.setMensaje(OperationMessages.FOUND.name());
            apiResponseDTO.setData(moduloDTOs);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthorizationException ex) {
            Logger.getLogger(AccionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
