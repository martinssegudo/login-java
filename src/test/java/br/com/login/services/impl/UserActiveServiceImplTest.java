package br.com.login.services.impl;

import br.com.login.entities.ActiveUserInfo;
import br.com.login.entities.User;
import br.com.login.exceptions.CreateActiveInfoException;
import br.com.login.exceptions.FindUserException;
import br.com.login.repository.UserActiveInfoRepository;
import br.com.login.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserActiveServiceImplTest {
    @Mock
    UserActiveInfoRepository userActiveInfoRepository;
    @Mock
    UserService userService;
    @InjectMocks
    UserActiveInfoServiceImpl userActiveRepository;

    @Before
    public void config(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void createUserActiveInfoSucess() throws FindUserException, CreateActiveInfoException {
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
        when(userService.findUserByLogin(anyString()))
                .thenReturn(userReturned);

        when(userActiveInfoRepository.save(any(ActiveUserInfo.class)))
                .thenReturn(newActive);

        userActiveRepository.saveNewActiveInfo("luiz_segundo");
    }

    @Test(expected = CreateActiveInfoException.class)
    public void createUserActiveInfoErroUserNotFound() throws FindUserException, CreateActiveInfoException {
        when(userService.findUserByLogin(anyString()))
                .thenThrow(FindUserException.class);

        userActiveRepository.saveNewActiveInfo("luiz_segundo");
    }

    @Test
    public void desativateUserSucess() throws FindUserException, CreateActiveInfoException {
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
        when(userActiveInfoRepository.findByLogin(anyString()))
                .thenReturn(newActive);

        userActiveRepository.removeAccess("luiz_segundo");
    }

    @Test(expected = CreateActiveInfoException.class)
    public void desativateUserErroNotFound() throws CreateActiveInfoException {

        when(userActiveInfoRepository.findByLogin(anyString()))
                .thenThrow(new EmptyResultDataAccessException(0));

        userActiveRepository.removeAccess("luiz_segundo");
    }

}
