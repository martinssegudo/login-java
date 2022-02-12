package br.com.login.services.impl;

import br.com.login.entities.User;
import br.com.login.exceptions.CreateUserException;
import br.com.login.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
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
                .startDate(LocalDate.now())
                .build();
        User userReturned = User.builder()
                .id(1L)
                .name("Luiz Segundo")
                .login("luiz_segundo")
                .passsword("12345678")
                .birthDay(LocalDate.now())
                .startDate(LocalDate.now())
                .build();

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
                .startDate(null)
                .build();

        User createdUser = usuarioService.saveUser(user);
    }
}