package br.com.login.services;

import br.com.login.entities.Role;
import br.com.login.exceptions.CreateRoleException;

public interface RoleService {
    Role saveRole(Role newRole) throws CreateRoleException;
}
