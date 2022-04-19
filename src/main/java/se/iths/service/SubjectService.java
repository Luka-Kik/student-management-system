package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Subject subject) {
        entityManager.persist(subject);
    }

    public List<Subject> getAll() {
        return entityManager.createQuery("SELECT i from Subject i", Subject.class).getResultList();
    }

    public void update(Subject subject) {
        entityManager.merge(subject);
    }

    public void delete(Long id) {
        Subject foundSubject = entityManager.find(Subject.class, id);
        entityManager.remove(foundSubject);
    }

    public List<Subject> findByName(String name) {
        String query = "SELECT i FROM Subject i WHERE i.name = :name";

        return entityManager.createQuery(query, Subject.class)
                .setParameter("name", name).getResultList();
    }
}
