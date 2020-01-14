package com.caix.dao;

import com.caix.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface RoleDao  {

    int addRole(Role role);
    int deleteRole(int id);
    int updateRole(Role role);
    Role getRoleById(int id);
    Role getRoleByName(String name);
    List<Role> getRoles();

}
