package com.example.demo.store;

import com.example.demo.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    private final EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Employee emp) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(emp);
    }

    @Override
    @Transactional
    public List<Employee> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> q = currentSession.createQuery("FROM Employee", Employee.class);

        return q.list();
    }

    @Override
    @Transactional
    public Optional<Employee> findById(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> q = currentSession.createQuery("FROM Employee WHERE id=:id", Employee.class);
        q.setParameter("id", id);

        return q.uniqueResultOptional();
    }

    @Override
    @Transactional
    public void delete(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> q = currentSession.createQuery("DELETE FROM Employee WHERE id=:id", Employee.class);
        q.setParameter("id", id);

        q.executeUpdate();
    }
}
