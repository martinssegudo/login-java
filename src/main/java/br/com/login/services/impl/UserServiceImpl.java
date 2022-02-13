package br.com.login.services.impl;

import br.com.login.entities.User;
import br.com.login.exceptions.CreateUserException;
import br.com.login.exceptions.DateUtilException;
import br.com.login.exceptions.FindUserException;
import br.com.login.exceptions.StringUtilException;
import br.com.login.repository.UserRepository;
import br.com.login.services.UserService;
import br.com.login.services.util.DateUtil;
import br.com.login.services.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User newUser) throws CreateUserException {
        try {
            StringUtil.checkStringLength(newUser.getName(), 10L);
            StringUtil.checkStringIsValid(newUser.getLogin());
            StringUtil.checkStringLength(newUser.getPasssword(), 8L);
            DateUtil.checkLocalDateNotNull(newUser.getBirthDay());
            DateUtil.checkLocalDateNotNull(newUser.getStartDate());
        }catch (StringUtilException | DateUtilException ex){
            throw new CreateUserException();
        }
        return userRepository.save(newUser);
    }

    @Override
    public User findUserByLogin(String login) throws FindUserException {
        User user = userRepository.findByLogin(login);
        if(user == null)
            throw new FindUserException();
        return user;
    }


}
