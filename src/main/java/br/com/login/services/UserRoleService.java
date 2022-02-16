package br.com.login.services;

import br.com.login.entities.UserRole;
import br.com.login.exceptions.RoleAssociateExption;

public interface UserRoleService {

    UserRole associateUserToRole(String login, String technicalNameRole) throws RoleAssociateExption;
    void removeRoleToUser(String login, String technicalNameRole) throws RoleAssociateExption;
}
