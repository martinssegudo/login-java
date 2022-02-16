package br.com.login.services.impl;

import br.com.login.entities.Role;
import br.com.login.entities.User;
import br.com.login.entities.UserRole;
import br.com.login.exceptions.FindRoleException;
import br.com.login.exceptions.FindUserException;
import br.com.login.exceptions.RoleAssociateExption;
import br.com.login.repository.UserRoleRepository;
import br.com.login.services.RoleService;
import br.com.login.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserRoleServiceImplTest {
    @Mock
    UserService userService;
    @Mock
    RoleService roleService;
    @Mock
    UserRoleRepository userRoleRepository;
    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @Before
    public void config(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void associateRoleToUserSucess() throws FindUserException, RoleAssociateExption, FindRoleException {
        Role role = Role.builder()
                .id(1L)
                .name("Admin")
                .technicalName("Admin")
                .startDate(LocalDateTime.now())
                .build();
        User user = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        when(userService.findUserByLogin(anyString()))
                .thenReturn(user);
        when(roleService.findBytechnicalName(anyString()))
                .thenReturn(role);
        when(userRoleRepository.save(any(UserRole.class)))
                .thenReturn(UserRole.builder()
                        .id(1L)
                        .user(user)
                        .role(role)
                        .startData(LocalDateTime.now())
                        .build());
        UserRole userRole = userRoleService
                .associateUserToRole("login_luiz", "ROLE_ADMIN");

        assertNotNull(userRole.getStartData());
    }

    @Test(expected = RoleAssociateExption.class)
    public void associateRoleToUserErroUserNotFound() throws FindUserException, RoleAssociateExption {
        when(userService.findUserByLogin(anyString()))
                .thenThrow(FindUserException.class);

        userRoleService.associateUserToRole("login_luiz", "ROLE_ADMIN");
    }

    @Test(expected = RoleAssociateExption.class)
    public void associateRoleToUserErroRoleNotFound() throws FindUserException, RoleAssociateExption, FindRoleException {
        User user = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        when(userService.findUserByLogin(anyString()))
                .thenReturn(user);
        when(roleService.findBytechnicalName(anyString()))
                .thenThrow(FindRoleException.class);

        userRoleService.associateUserToRole("login_luiz", "ROLE_ADMIN");
    }

    @Test
    public void removeRoleToUserSucess() throws RoleAssociateExption {
        Role role1 = Role.builder()
                .id(1L)
                .name("Admin")
                .technicalName("ADMIN")
                .startDate(LocalDateTime.now())
                .build();
        Role role2 = Role.builder()
                .id(2L)
                .name("Admin")
                .technicalName("USER")
                .startDate(LocalDateTime.now())
                .build();
        User user = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        UserRole userRoleFinded1 = UserRole.builder()
                .id(1L)
                .startData(LocalDateTime.now())
                .role(role1)
                .user(user)
                .build();

        UserRole userRoleFinded2 = UserRole.builder()
                .id(2L)
                .startData(LocalDateTime.now())
                .role(role2)
                .user(user)
                .build();

        when(userRoleRepository.findByLogin(anyString()))
                .thenReturn(Arrays.asList(userRoleFinded1,userRoleFinded2));

        UserRole userRoleMoified = userRoleFinded1;
        userRoleMoified.setEndData(LocalDateTime.now());
        when(userRoleRepository.save(any(UserRole.class)))
                .thenReturn(userRoleMoified);

        userRoleService.removeRoleToUser("login_luiz", "ADMIN");
    }

    @Test(expected = RoleAssociateExption.class)
    public void removeRoleToUserErroUserNotFound() throws RoleAssociateExption {

        when(userRoleRepository.findByLogin(anyString()))
                .thenThrow(EmptyResultDataAccessException.class);

        userRoleService.removeRoleToUser("login_luiz", "ADMIN");
    }

    @Test(expected = RoleAssociateExption.class)
    public void removeRoleToUserErroRoleNotFound() throws RoleAssociateExption {
        Role role1 = Role.builder()
                .id(1L)
                .name("Admin")
                .technicalName("ADMIN")
                .startDate(LocalDateTime.now())
                .build();
        Role role2 = Role.builder()
                .id(2L)
                .name("Admin")
                .technicalName("USER")
                .startDate(LocalDateTime.now())
                .build();
        User user = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        UserRole userRoleFinded1 = UserRole.builder()
                .id(1L)
                .startData(LocalDateTime.now())
                .role(role1)
                .user(user)
                .build();

        UserRole userRoleFinded2 = UserRole.builder()
                .id(2L)
                .startData(LocalDateTime.now())
                .role(role2)
                .user(user)
                .build();

        when(userRoleRepository.findByLogin(anyString()))
                .thenReturn(Arrays.asList(userRoleFinded1,userRoleFinded2));

        userRoleService.removeRoleToUser("login_luiz", "XPTO");
    }
}
