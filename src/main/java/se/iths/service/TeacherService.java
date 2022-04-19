package se.iths.service;

import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public List<Teacher> getAll() {
        return entityManager.createQuery("SELECT i from Teacher i", Teacher.class).getResultList();
    }

    public void update(Teacher student) {
        entityManager.merge(student);
    }

    public void delete(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
    }

    public List<Teacher> findByLastname(String lastName) {
        String query = "SELECT i FROM Teacher i WHERE i.lastName = :lastName";

        return entityManager.createQuery(query, Teacher.class)
                .setParameter("lastName", lastName).getResultList();
    }
}
