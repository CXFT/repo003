package com.caix.service;

import com.caix.dao.RoleDao;
import com.caix.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {
    @Resource
    private RoleDao roleDao;

    public boolean addRole(Role role) {
       return  roleDao.addRole(role) == 1;
    }

    public boolean deleteRole(int id) {
        return roleDao.deleteRole(id) == 1;
    }

    public boolean updateRole(Role role) {
        return roleDao.updateRole(role) == 1;
    }

    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    public List<Role> getRoles() {
        return roleDao.getRoles();
    }
}
