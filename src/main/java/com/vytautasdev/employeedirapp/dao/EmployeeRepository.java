package com.vytautasdev.employeedirapp.dao;

import com.vytautasdev.employeedirapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    //  method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();

    public List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String firstName, String lastName);


}
