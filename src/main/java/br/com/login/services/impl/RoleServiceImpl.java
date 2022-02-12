package br.com.login.services.impl;

import br.com.login.entities.Role;
import br.com.login.exceptions.CreateRoleException;
import br.com.login.exceptions.StringUtilException;
import br.com.login.repository.RoleRepository;
import br.com.login.services.RoleService;
import br.com.login.services.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role newRole) throws CreateRoleException {
        try {
            StringUtil.checkStringLength(newRole.getName(), 5L);
            StringUtil.checkStringLength(newRole.getTechnicalName(), 5L);
        } catch (StringUtilException e) {
            throw new CreateRoleException();
        }
        return roleRepository.save(newRole);
    }
}
