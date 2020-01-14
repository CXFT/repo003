package com.caix.dao;

import com.caix.model.Department;
import java.util.List;

public interface DepartmentDao {

    int addDepartment(Department department);
    int deleteDepartment(int id);
    int updateDepartment(Department department);
    Department getDepartmentById(int id);
    Department getDepartmentByName(String name);
    List<Department> getDepartments();

}
