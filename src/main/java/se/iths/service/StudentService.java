package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Student student) {
        entityManager.persist(student);
    }

    public List<Student> getAll() {
        return entityManager.createQuery("SELECT i from Student i", Student.class).getResultList();
    }

    public void update(Student student) {
        entityManager.merge(student);
    }

    public void delete(Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        entityManager.remove(foundStudent);
    }

    public List<Student> findByLastname(String lastName) {
        String query = "SELECT i FROM Student i WHERE i.lastName = :lastName";

        return entityManager.createQuery(query, Student.class)
                .setParameter("lastName", lastName).getResultList();
    }
}
