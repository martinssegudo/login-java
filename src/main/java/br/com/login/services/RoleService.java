package br.com.login.services;

import br.com.login.entities.Role;
import br.com.login.exceptions.CreateRoleException;
import br.com.login.exceptions.FindRoleException;

public interface RoleService {
    Role saveRole(Role newRole) throws CreateRoleException;
    Role findBytechnicalName(String technicalName) throws FindRoleException;
}
