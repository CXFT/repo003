package com.caix.dao;

import com.caix.model.Department;
import com.caix.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentDaoTest {

    @Resource
    private  DepartmentService departmentService;

    @Test
    public void addDepartmentTest(){
        assertTrue(departmentService.addDepartment(new Department("测试部","北京市")));
    }

    @Test
    public void deleteDepartmentTest(){
        assertTrue(departmentService.deleteDepartment(6));
    }

    @Test
    public void updateDepartmentTest(){
        assertTrue(departmentService.updateDepartment(new Department(17,"测试部1","南京市")));
    }

    @Test
    public void getDepartmentByIdTest(){
        System.out.println(departmentService.getDepartmentById(1));
    }

    @Test
    public void getDepartments(){
        System.out.println(departmentService.getDepartments());
    }

}