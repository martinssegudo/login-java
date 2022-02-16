package br.com.login.services.impl;

import br.com.login.entities.ActiveUserInfo;
import br.com.login.entities.User;
import br.com.login.exceptions.CreateActiveInfoException;
import br.com.login.exceptions.CreateUserException;
import br.com.login.exceptions.FindUserException;
import br.com.login.repository.UserRepository;
import br.com.login.services.UserActiveInfoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @InjectMocks
    UserServiceImpl usuarioService;

    @Before
    public void config(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createNewUserWithSucess() throws Exception {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        User userReturned = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
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

        User createdUser = usuarioService.saveUser(user);
        assertNotNull(createdUser.getId());
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroNameLessTenCharacters() throws CreateUserException {
        User user = User.builder()
                .name("Luiz")
                .build();

        User createdUser = usuarioService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroNameNull() throws CreateUserException {
        User user = User.builder()
                .name(null)
                .build();

        User createdUser = usuarioService.saveUser(user);
    }


    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroLoginNull() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login(null)
                .build();

        User createdUser = usuarioService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroPasswordNull() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword(null)
                .build();

        User createdUser = usuarioService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroPasswordLessEigth() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("123456")
                .build();

        User createdUser = usuarioService.saveUser(user);
    }


    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroBirthDayNull() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(null)
                .build();

        User createdUser = usuarioService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroStartDateNull() throws CreateUserException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .createDate(null)
                .build();

        User createdUser = usuarioService.saveUser(user);
    }

    @Test(expected = CreateUserException.class)
    public void createNewUserWithErroSaveNewActiveInfo() throws CreateUserException, CreateActiveInfoException {
        User user = User.builder()
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();

        User userReturned = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .createDate(LocalDateTime.now())
                .build();
        when(userRepository.save(any(User.class))).thenReturn(userReturned);
        doThrow(CreateActiveInfoException.class).when(userActiveInfoService)
                .saveNewActiveInfo("luiz_segundo");

        User createdUser = usuarioService.saveUser(user);
    }

    @Test
    public void findUserByLoginSucess() throws FindUserException {
        when(userRepository.findByLogin(anyString()))
                .thenReturn(User.builder()
                                .name("Luiz Segundo")
                                .login("luiz_segundo")
                                .passsword("12345678")
                                .birthDay(LocalDate.now())
                                .createDate(LocalDateTime.now())
                                .build());

        User findedUser = usuarioService.findUserByLogin("LoginUser");

        assertNotNull(findedUser);
    }

    @Test(expected = FindUserException.class)
    public void findUserByLoginError() throws FindUserException {
        when(userRepository.findByLogin(anyString()))
                .thenThrow(EmptyResultDataAccessException.class);

        User findedUser = usuarioService.findUserByLogin("LoginUser");
    }


}