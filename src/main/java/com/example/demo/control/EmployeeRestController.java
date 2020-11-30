package com.example.demo.control;

import com.example.demo.ex.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeSvc service;

    @Autowired
    public EmployeeRestController(EmployeeSvc service) {
        this.service = service;
    }

    @PostMapping("/employees")
    public Employee add(@RequestBody Employee emp) {
        service.add(emp);
        return emp;
    }

    @GetMapping("/employees")
    public List<Employee> getList() {
        return service.getAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch(Exception ex) {
            throw new EmployeeNotFoundException("Employee with id: " + id + " not found!");
        }
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee emp) {
        service.update(emp);
        return emp;
    }

    @DeleteMapping("/employees/{id}")
    public String delete(@PathVariable int id) {
        try {
            service.delete(id);
        } catch(Exception ex) {
            throw new EmployeeNotFoundException("Employee with id: " + id + " not found!");
        }
        return "Deleted employee by id: " + id;
    }
}
