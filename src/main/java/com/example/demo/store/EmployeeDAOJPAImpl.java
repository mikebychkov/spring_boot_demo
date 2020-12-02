package com.example.demo.store;

import com.example.demo.model.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    private final EntityManager entityManager;

    public EmployeeDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Employee emp) {

        Employee dbEmp = entityManager.merge(emp);

        emp.setId(dbEmp.getId());
    }

    @Override
    @Transactional
    public List<Employee> findAll() {

        TypedQuery<Employee> q = entityManager.createQuery("FROM Employee", Employee.class);

        return q.getResultList();
    }

    @Override
    @Transactional
    public Optional<Employee> findById(int id) {

        TypedQuery<Employee> q = entityManager.createQuery("FROM Employee WHERE id=:id", Employee.class);
        q.setParameter("id", id);

        Optional<Employee> rsl = Optional.ofNullable(q.getSingleResult());

        return rsl;
    }

    @Override
    @Transactional
    public void delete(int id) {

        TypedQuery<Employee> q = entityManager.createQuery("DELETE FROM Employee WHERE id=:id", Employee.class);
        q.setParameter("id", id);
        q.executeUpdate();
    }
}
