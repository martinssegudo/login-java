package br.com.login.services;

import br.com.login.entities.Role;
import br.com.login.entities.UserRole;
import br.com.login.exceptions.RoleAssociateExption;

import java.util.List;

public interface UserRoleService {

    UserRole associateUserToRole(String login, String technicalNameRole) throws RoleAssociateExption;
    void removeRoleToUser(String login, String technicalNameRole) throws RoleAssociateExption;
    List<UserRole> findRolesByLogin(String login) throws RoleAssociateExption;
}
