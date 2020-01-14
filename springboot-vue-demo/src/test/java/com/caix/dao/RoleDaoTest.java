package com.caix.dao;

import com.caix.model.Role;
import com.caix.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleDaoTest {

    @Resource
    private RoleService roleService;

    @Test
    public void addRoleTest(){
        assertTrue(roleService.addRole(new Role("董事长")));
    }

    @Test
    public void deleteRoleTest(){
        assertTrue(roleService.deleteRole(6));
    }

    @Test
    public void updateRoleTest(){
        assertTrue(roleService.updateRole(new Role(6,"董事")));
    }

    @Test
    public void getRoleByIdTest(){
        System.out.println(roleService.getRoleById(1));
    }

    @Test
    public void getRoles(){
        System.out.println(roleService.getRoles());
    }

    /*一级缓存测试*/
    @Test
    public void testOneCache(){
        Role role1 = roleService.getRoleById(1);
        System.out.println(role1);

        Role role2 = roleService.getRoleById(2);
        System.out.println(role2);

        System.out.println(role1 .equals(role2) );
    }

}