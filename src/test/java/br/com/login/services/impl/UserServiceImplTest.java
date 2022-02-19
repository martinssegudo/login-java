package br.com.login.services.impl;

import br.com.login.dtos.LoginInfoDTO;
import br.com.login.entities.ActiveUserInfo;
import br.com.login.entities.Role;
import br.com.login.entities.User;
import br.com.login.entities.UserRole;
import br.com.login.exceptions.CreateActiveInfoException;
import br.com.login.exceptions.CreateUserException;
import br.com.login.exceptions.FindUserException;
import br.com.login.exceptions.RoleAssociateExption;
import br.com.login.repository.UserRepository;
import br.com.login.services.UserActiveInfoService;
import br.com.login.services.UserRoleService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserActiveInfoService userActiveRepository;
    @Mock
    UserActiveInfoService userActiveInfoService;
    @Mock
    UserRoleService userRoleService;
    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void config(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createNewUserWithSucess() throws Exception {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        User userReturned = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();

        ActiveUserInfo newActive = ActiveUserInfo.builder()
                .user(userReturned)
                .startDate(LocalDateTime.now())
                .build();


        doNothing().when(userActiveRepository).saveNewActiveInfo(anyString());

        when(userRepository.save(any(User.class)))
                .thenReturn(userReturned);

        User createdUser = userService.saveUser(user);
        assertNotNull(createdUser.getId());
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroNameLessTenCharacters() throws CreateUserException {
        User user = User.builder()
                .name("Luiz")
                .build();

        User createdUser = userService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroNameNull() throws CreateUserException {
        User user = User.builder()
                .name(null)
                .build();

        User createdUser = userService.saveUser(user);
    }


    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroLoginNull() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login(null)
                .build();

        User createdUser = userService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroPasswordNull() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password(null)
                .build();

        User createdUser = userService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroPasswordLessEigth() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password("123456")
                .build();

        User createdUser = userService.saveUser(user);
    }


    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroBirthDayNull() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password("12345678")
                .birthDay(null)
                .build();

        User createdUser = userService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroStartDateNull() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password("12345678")
                .birthDay(LocalDate.now())
                .createDate(null)
                .build();

        User createdUser = userService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroSaveNewActiveInfo() throws CreateUserException, CreateActiveInfoException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();

        User userReturned = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        when(userRepository.save(any(User.class))).thenReturn(userReturned);
        doThrow(CreateActiveInfoException.class).when(userActiveInfoService)
                .saveNewActiveInfo("luiz_segundo");

        User createdUser = userService.saveUser(user);
    }

    @Test
    public void findUserByLoginSucess() throws FindUserException {
        when(userRepository.findByLogin(anyString()))
                .thenReturn(User.builder()
                                .name("Luiz Segundo")
                                .login("luiz_segundo")
                                .password("12345678")
                                .birthDay(LocalDate.now())
                                .createDate(LocalDateTime.now())
                                .build());

        User findedUser = userService.findUserByLogin("LoginUser");

        assertNotNull(findedUser);
    }

    @Test(expected = FindUserException.class)
    public void findUserByLoginError() throws FindUserException {
        when(userRepository.findByLogin(anyString()))
                .thenThrow(EmptyResultDataAccessException.class);

        User findedUser = userService.findUserByLogin("LoginUser");
    }


    @Test
    public void userLoginSucess() throws RoleAssociateExption {
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
                .password("12345678")
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
        when(userRepository.findByLoginAndPassword(anyString(),anyString()))
                .thenReturn(user);
        when(userRoleService.findRolesByLogin(anyString()))
                .thenReturn(Arrays.asList(userRoleFinded1,userRoleFinded2));
        LoginInfoDTO loginInfo = userService.loginUser("login","senha");
        assertNotNull(loginInfo.getRoles());
    }

    @Test(expected = RoleAssociateExption.class)
    public void userLoginErrorUserNotFound() throws RoleAssociateExption {
        when(userRepository.findByLoginAndPassword(anyString(),anyString()))
                .thenThrow(EmptyResultDataAccessException.class);
        LoginInfoDTO loginInfo = userService.loginUser("login","senha");
        assertNotNull(loginInfo.getRoles());
    }

    @Test(expected = RoleAssociateExption.class)
    public void userLoginErrorUserRolesNotFound() throws RoleAssociateExption {
        User user = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .password("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        when(userRepository.findByLoginAndPassword(anyString(),anyString()))
                .thenReturn(user);
        when(userRoleService.findRolesByLogin(anyString()))
                .thenThrow(EmptyResultDataAccessException.class);
        LoginInfoDTO loginInfo = userService.loginUser("login","senha");
        assertNotNull(loginInfo.getRoles());
    }
}