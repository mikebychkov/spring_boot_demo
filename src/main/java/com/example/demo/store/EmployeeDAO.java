package com.example.demo.store;

import com.example.demo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    void save(Employee emp);
    List<Employee> findAll();
    Optional<Employee> findById(int id);
    void delete(int id);
}
