package com.caix.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Employee implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private int gender;
    private Date dob;
    private String phone;
    private String email;
    private Department department;
    private Role role;

    public Employee() {
    }

    public Employee(String name, String password, int gender, Date dob, String phone, String email, Department department, Role role) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.role = role;
    }

    public Employee(Integer id, String name, String password, int gender, Date dob, String phone, String email, Department department, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", role=" + role +
                '}';
    }
}
