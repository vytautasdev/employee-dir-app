package com.vytautasdev.employeedirapp.dao;

import com.vytautasdev.employeedirapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    //  method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();

//    public List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String firstName, String lastName);

//    @Query(value = "select * from Customer where is_deleted = false", nativeQuery = true)
    @Query("SELECT emp FROM Employee emp WHERE emp.lastName = ?1")
    List<Employee> findByLastNameContainsAllIgnoreCase(String lastName);

}
