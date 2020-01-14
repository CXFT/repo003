package com.caix.dao;

import com.caix.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface EmployeeDao {

    int addEmployee(Employee employee);
    int deleteEmployee(int id);
    int updateEmployee(Employee employee);
    Employee getEmployeeById(int id);
    List<Employee> getEmployees();
    Employee getEmployeeByPhone(String phone);
    Employee getEmployeeByName(String name);
}
