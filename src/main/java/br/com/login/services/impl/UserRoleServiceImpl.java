package br.com.login.services.impl;

import br.com.login.entities.Role;
import br.com.login.entities.User;
import br.com.login.entities.UserRole;
import br.com.login.exceptions.FindRoleException;
import br.com.login.exceptions.FindUserException;
import br.com.login.exceptions.RoleAssociateExption;
import br.com.login.repository.UserRoleRepository;
import br.com.login.services.RoleService;
import br.com.login.services.UserRoleService;
import br.com.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository,
                               UserService userService,
                               RoleService roleService){
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public UserRole associateUserToRole(String login, String technicalNameRole) throws RoleAssociateExption {
        User user = null;
        Role role = null;
        try {
            user = userService.findUserByLogin(login);
            role = roleService.findBytechnicalName(technicalNameRole);
        } catch (FindUserException | FindRoleException e) {
            throw new RoleAssociateExption();
        }

        return userRoleRepository.save(UserRole.builder()
                .user(user)
                .role(role)
                .startData(LocalDateTime.now())
                .build());

    }
}
