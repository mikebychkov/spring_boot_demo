package com.example.demo.store;

import com.example.demo.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeStore extends CrudRepository<Employee, Integer> {
}
