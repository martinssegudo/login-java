package br.com.login.controller.impl;

import br.com.login.controller.dtos.LoginDTO;
import br.com.login.controller.dtos.UserCreateDTO;
import br.com.login.controller.dtos.UserRoleAssociateDTO;
import br.com.login.dtos.LoginInfoDTO;
import br.com.login.entities.User;
import br.com.login.entities.UserRole;
import br.com.login.exceptions.CreateUserException;
import br.com.login.exceptions.DateUtilException;
import br.com.login.exceptions.RoleAssociateExption;
import br.com.login.services.UserRoleService;
import br.com.login.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserController {

    @Mock
    private UserService userService;
    @Mock
    private UserRoleService userRoleService;
    @InjectMocks
    private UserControllerImpl userControlle;

    @Before
    public void config(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void loginSucess() throws RoleAssociateExption {
        when(userService.loginUser(anyString(), anyString()))
                .thenReturn(LoginInfoDTO.builder().build());

        ResponseEntity<LoginInfoDTO> response =  userControlle.login(LoginDTO.builder()
                .login("login")
                .password("senha")
                .build());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    @Test(expected = RoleAssociateExption.class)
    public void loginErro() throws RoleAssociateExption {
        when(userService.loginUser(anyString(), anyString()))
                .thenThrow(RoleAssociateExption.class);

        ResponseEntity<LoginInfoDTO> response =  userControlle.login(LoginDTO.builder()
                .login("login")
                .password("senha")
                .build());
    }

    @Test
    public void createUserSucess() throws CreateUserException, DateUtilException {
        when(userService.saveUser(any(User.class)))
                .thenReturn(User.builder().build());
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .name("Luiz")
                .login("luiz")
                .passsword("123456")
                .birthDay("12/08/2000")
                .build();
        ResponseEntity reposnse = userControlle.save(userCreateDTO);
        assertEquals(reposnse.getStatusCode(), HttpStatus.CREATED);
    }

    @Test(expected = DateUtilException.class)
    public void createUserErrorBirthDayNull() throws CreateUserException, DateUtilException {
        when(userService.saveUser(any(User.class)))
                .thenReturn(User.builder().build());
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .name("Luiz")
                .login("luiz")
                .passsword("123456")
                .birthDay(null)
                .build();
        ResponseEntity reposnse = userControlle.save(userCreateDTO);
        assertEquals(reposnse.getStatusCode(), HttpStatus.CREATED);
    }

    @Test(expected = CreateUserException.class)
    public void createUserErrorService() throws CreateUserException, DateUtilException {
        when(userService.saveUser(any(User.class)))
                .thenThrow(CreateUserException.class);
        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .name("Luiz")
                .login("luiz")
                .passsword("123456")
                .birthDay("12/08/2000")
                .build();
        ResponseEntity reposnse = userControlle.save(userCreateDTO);
        assertEquals(reposnse.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void associateRolToUserSucess() throws RoleAssociateExption {
        when(userRoleService.associateUserToRole(anyString(),anyString()))
                .thenReturn(UserRole.builder().build());
        UserRoleAssociateDTO userRoleAssociateDTO =
                UserRoleAssociateDTO.builder()
                        .login("login")
                        .role("ADMIN")
                        .build();
        ResponseEntity reposnse = userControlle.associateRole(userRoleAssociateDTO);
        assertEquals(reposnse.getStatusCode(), HttpStatus.CREATED);
    }

    @Test(expected = RoleAssociateExption.class)
    public void associateRolToUserErro() throws RoleAssociateExption {
        when(userRoleService.associateUserToRole(anyString(),anyString()))
                .thenThrow(RoleAssociateExption.class);
        UserRoleAssociateDTO userRoleAssociateDTO =
                UserRoleAssociateDTO.builder()
                        .login("login")
                        .role("ADMIN")
                        .build();
        ResponseEntity reposnse = userControlle.associateRole(userRoleAssociateDTO);
    }
}
