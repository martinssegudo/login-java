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

import java.time.LocalDate;
import java.time.LocalDateTime;

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
                .startDate(LocalDate.now())
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
}
