package br.com.login.services.impl;

import br.com.login.entities.User;
import br.com.login.exceptions.*;
import br.com.login.repository.UserRepository;
import br.com.login.services.UserActiveInfoService;
import br.com.login.services.UserService;
import br.com.login.services.util.DateUtil;
import br.com.login.services.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserActiveInfoService userActiveInfoService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserActiveInfoService userActiveInfoService){
        this.userRepository = userRepository;
        this.userActiveInfoService = userActiveInfoService;
    }

    @Override
    public User saveUser(User newUser) throws CreateUserException {
        User user = null;
        try {
            StringUtil.checkStringLength(newUser.getName(), 10L);
            StringUtil.checkStringIsValid(newUser.getLogin());
            StringUtil.checkStringLength(newUser.getPasssword(), 8L);
            DateUtil.checkLocalDateNotNull(newUser.getBirthDay());
            DateUtil.checkLocalDateTimeNotNull(newUser.getCreateDate());
            user = userRepository.save(newUser);
            userActiveInfoService.saveNewActiveInfo(newUser.getLogin());
        }catch (StringUtilException | DateUtilException | CreateActiveInfoException ex){
            throw new CreateUserException();
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws FindUserException {
        User user = null;
        try{
            user = userRepository.findByLogin(login);
        }catch (EmptyResultDataAccessException ex){
            throw new FindUserException();
        }

        return user;
    }


}
