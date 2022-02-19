package br.com.login.controller;

import br.com.login.controller.dtos.LoginDTO;
import br.com.login.controller.dtos.UserCreateDTO;
import br.com.login.controller.dtos.UserRoleAssociateDTO;
import br.com.login.dtos.LoginInfoDTO;
import br.com.login.exceptions.CreateUserException;
import br.com.login.exceptions.DateUtilException;
import br.com.login.exceptions.RoleAssociateExption;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import javax.persistence.MapsId;

public interface UserController {

    @ApiOperation(value = "Realiza login de um usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna as informacoes do usuario"),
            @ApiResponse(code = 500, message = "Retornara um erro nao tratado"),
    })
    ResponseEntity<LoginInfoDTO> login(LoginDTO login) throws RoleAssociateExption;

    @ApiOperation(value = "Cadastra novo usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna status 201 se o usuario for criado"),
            @ApiResponse(code = 500, message = "Retornara um erro nao tratado"),
    })
    ResponseEntity save(UserCreateDTO userCreateDTO) throws CreateUserException, DateUtilException;

    @ApiOperation(value = "Cadastra perfil ao usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna status 201 se o perfil for associado"),
            @ApiResponse(code = 500, message = "Retornara um erro nao tratado"),
    })
    ResponseEntity associateRole(UserRoleAssociateDTO userRoleAssociateDTO) throws RoleAssociateExption;
}
