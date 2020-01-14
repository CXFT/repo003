package com.caix.dao;

import com.caix.model.Employee;
import com.caix.service.DepartmentService;
import com.caix.service.EmployeeService;
import com.caix.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDaoTest {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private RoleService roleService;
    @Resource
    private DepartmentService departmentService;

    @Test
    public void addEmployeeTest(){
        assertTrue(employeeService.addEmployee(new Employee(
                "王军",
                "wj123",
                1,
                new Date(),
                "12345678910",
                "1452087@qq.com",
                departmentService.getDepartmentById(1),
                roleService.getRoleById(2)
        )));
    }

    @Test
    public void updateEmployeeTest(){
        assertTrue(employeeService.updateEmployee(new Employee(
                5,
                "王志军",
                "wzj123",
                1,
                new Date(),
                "12345678910",
                "1452087@qq.com",
                departmentService.getDepartmentById(1),
                roleService.getRoleById(2)
        )));
    }

    @Test
    public void getEmployeeById(){
        System.out.println(employeeService.getEmployeeById(6));
    }

    @Test
    public void getEmployees(){
        System.out.println(employeeService.getEmployees());
    }


}