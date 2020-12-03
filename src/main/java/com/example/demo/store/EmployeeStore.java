package com.example.demo.store;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="emps")
public interface EmployeeStore extends JpaRepository<Employee, Integer> {

    public List<Employee> findAllByOrderByNameAsc();
}
