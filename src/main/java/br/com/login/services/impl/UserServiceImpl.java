package br.com.login.services.impl;

import br.com.login.dtos.LoginInfoDTO;
import br.com.login.entities.User;
import br.com.login.entities.UserRole;
import br.com.login.exceptions.*;
import br.com.login.repository.UserRepository;
import br.com.login.services.UserActiveInfoService;
import br.com.login.services.UserRoleService;
import br.com.login.services.UserService;
import br.com.login.services.util.DateUtil;
import br.com.login.services.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserActiveInfoService userActiveInfoService;
    private UserRoleService userRoleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserActiveInfoService userActiveInfoService,
                           UserRoleService userRoleService){
        this.userRepository = userRepository;
        this.userActiveInfoService = userActiveInfoService;
        this.userRoleService = userRoleService;
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

    @Override
    public LoginInfoDTO loginUser(String login, String password) throws RoleAssociateExption {
        User user = userRepository.findByLoginAndPassword(login,password);
        List<UserRole> userRoleList = userRoleService.findRolesByLogin(login);
        return LoginInfoDTO.builder()
                .user(user)
                .roles(userRoleList.stream()
                        .map(
                                userRole -> userRole.getRole()
                        ).collect(Collectors.toList()))
                .build();
    }


}
