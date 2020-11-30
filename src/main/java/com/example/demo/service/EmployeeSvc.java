package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.store.EmployeeStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeSvc {

    private EmployeeStore store;

    @Autowired
    public EmployeeSvc(EmployeeStore store) {
        this.store = store;
    }

    public void add(Employee emp) {
        store.save(emp);
    }

    public List<Employee> getAll() {
        List<Employee> rsl = new ArrayList<>();
        store.findAll().forEach(rsl::add);
        return rsl;
    }

    public Employee getById(int id) throws Exception {
        return store.findById(id).orElseThrow();
    }

    public void update(Employee emp) {
        store.save(emp);
    }

    public void delete(int id) throws Exception {
        store.delete(store.findById(id).orElseThrow());
    }
}
