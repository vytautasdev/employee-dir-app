package com.vytautasdev.employeedirapp.service;

import com.vytautasdev.employeedirapp.entity.Employee;
import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteById(int id);

    public List<Employee> searchBy(String name);
}
