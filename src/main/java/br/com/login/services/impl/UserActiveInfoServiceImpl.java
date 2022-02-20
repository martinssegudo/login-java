package br.com.login.services.impl;

import br.com.login.entities.ActiveUserInfo;
import br.com.login.entities.User;
import br.com.login.exceptions.CreateActiveInfoException;
import br.com.login.exceptions.FindUserException;
import br.com.login.repository.UserActiveInfoRepository;
import br.com.login.services.UserActiveInfoService;
import br.com.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserActiveInfoServiceImpl implements UserActiveInfoService {

    private UserActiveInfoRepository activeUserInfoRepository;

    @Autowired
    public UserActiveInfoServiceImpl(UserActiveInfoRepository activeUserInfoRepository){
        this.activeUserInfoRepository = activeUserInfoRepository;
    }

    @Override
    public void saveNewActiveInfo(User user) throws CreateActiveInfoException {
        ActiveUserInfo newActive = ActiveUserInfo.builder()
                .startDate(LocalDateTime.now())
                .user(user)
                .build();
            activeUserInfoRepository.save(newActive);
    }

    @Override
    public void removeAccess(String login) throws CreateActiveInfoException{
        try {
            ActiveUserInfo newActive = activeUserInfoRepository.findByLogin(login);
            newActive.setEndDate(LocalDateTime.now());
            activeUserInfoRepository.save(newActive);
        } catch (EmptyResultDataAccessException e) {
            throw new CreateActiveInfoException();
        }
    }
}
