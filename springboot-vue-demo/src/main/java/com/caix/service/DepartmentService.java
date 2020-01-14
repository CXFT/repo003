package com.caix.service;

import com.caix.dao.DepartmentDao;
import com.caix.model.Department;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentService {
    @Resource
    private DepartmentDao departmentDao;

    public boolean addDepartment(Department department) {
       return  departmentDao.addDepartment(department) == 1;
    }

    public boolean deleteDepartment(int id) {
        return departmentDao.deleteDepartment(id) == 1;
    }

    public boolean updateDepartment(Department department) {
        return departmentDao.updateDepartment(department) == 1;
    }

    public Department getDepartmentById(int id) {
        return departmentDao.getDepartmentById(id);
    }

    public Department getDepartmentByName(String name) {
        return departmentDao.getDepartmentByName(name);
    }

    public List<Department> getDepartments() {
        return departmentDao.getDepartments();
    }
}
