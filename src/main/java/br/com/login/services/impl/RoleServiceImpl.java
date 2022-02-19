package br.com.login.services.impl;

import br.com.login.entities.Role;
import br.com.login.exceptions.*;
import br.com.login.repository.RoleRepository;
import br.com.login.services.RoleService;
import br.com.login.util.DateUtil;
import br.com.login.services.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
            DateUtil.checkLocalDateTimeNotNull(newRole.getStartDate());
        } catch (StringUtilException | DateUtilException e) {
            throw new CreateRoleException();
        }
        return roleRepository.save(newRole);
    }

    public Role findBytechnicalName(String technicalName) throws FindRoleException {
        Role role = null;
        try{
            role = roleRepository.findByTechnicalName(technicalName);
        }catch (EmptyResultDataAccessException ex){
            throw new FindRoleException();
        }
        return role;
    }
}
