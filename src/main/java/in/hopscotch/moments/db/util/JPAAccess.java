package in.hopscotch.moments.db.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class JPAAccess {

    private EntityManager entityManager;

    public <T> T get(Class<T> entityClass, Object id) {
        return entityManager.find(entityClass, id);
    }

    public void save(Object entity) {
        entityManager.persist(entity);
    }

    public void update(Object entity) {
        entityManager.merge(entity);
    }

    public void delete(Object entity) {
        entityManager.merge(entity);
        entityManager.remove(entity);
    }

    public <T> List<T> find(CriteriaQuery<T> query) {
        return entityManager.createQuery(query).getResultList();
    }

    public <T> List<T> find(String query) {
        return entityManager.createQuery(query).getResultList();
    }

    public void flush() {
        entityManager.flush();
    }

    public CriteriaBuilder criteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
