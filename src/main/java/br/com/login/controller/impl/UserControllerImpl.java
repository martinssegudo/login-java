package br.com.login.controller.impl;

import br.com.login.controller.UserController;
import br.com.login.controller.dtos.LoginDTO;
import br.com.login.controller.dtos.UserCreateDTO;
import br.com.login.controller.dtos.UserRoleAssociateDTO;
import br.com.login.dtos.LoginInfoDTO;
import br.com.login.entities.User;
import br.com.login.exceptions.CreateUserException;
import br.com.login.exceptions.DateUtilException;
import br.com.login.exceptions.RoleAssociateExption;
import br.com.login.services.UserRoleService;
import br.com.login.services.UserService;
import br.com.login.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController("/user")
public class UserControllerImpl implements UserController {

    private UserService userService;
    private UserRoleService userRoleService;

    @Autowired
    public UserControllerImpl(UserService userService,
                              UserRoleService userRoleService){
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/login")
    @Override
    public ResponseEntity<LoginInfoDTO> login(@RequestBody LoginDTO login) throws RoleAssociateExption {
        LoginInfoDTO loginInfoDTO = userService
                .loginUser(login.getLogin(), login.getPassword());

        return ResponseEntity.ok(loginInfoDTO);
    }


    @PostMapping("/create")
    @Override
    public ResponseEntity save(@RequestBody UserCreateDTO userCreateDTO) throws CreateUserException, DateUtilException {
        userService.saveUser(User.builder()
                        .name(userCreateDTO.getName())
                        .password(userCreateDTO.getPasssword())
                        .birthDay(DateUtil.convertStgringToLocalDate(
                                userCreateDTO.getBirthDay(),
                                "dd/MM/yyyy"
                            )
                        )
                        .createDate(LocalDateTime.now())
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity associateRole(UserRoleAssociateDTO userRoleAssociateDTO) throws RoleAssociateExption {
        userRoleService.associateUserToRole(userRoleAssociateDTO.getLogin(),
                userRoleAssociateDTO.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
