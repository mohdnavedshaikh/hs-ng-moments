package in.hopscotch.moments.repository.impl;

import static org.springframework.core.GenericTypeResolver.resolveTypeArguments;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaQuery;

import in.hopscotch.moments.db.util.JPAAccess;

public abstract class AbstractRepository<T> {

    protected final Class<T> entityClass;

    @Inject
    protected JPAAccess jpaAccess;

    @SuppressWarnings("unchecked")
    public AbstractRepository() {
        Class<?>[] arguments = resolveTypeArguments(getClass(), AbstractRepository.class);
        entityClass = (Class<T>) arguments[0];
    }

    public T get(Object id) {
        T entity = jpaAccess.get(entityClass, id);
        return entity;
    }

    public void save(T entity) {
        jpaAccess.save(entity);
    }

    public void update(T entity) {
        jpaAccess.update(entity);
    }

    public void delete(T entity) {
        jpaAccess.delete(entity);
    }

    public List<T> find(CriteriaQuery<T> query) {
        return jpaAccess.find(query);
    }

    public List<T> find(String query) {
        List<T> result = jpaAccess.find(query, entityClass);
        return result;
    }

    public List<T> findByPagination(String query, int pageNo, int pageSize) {
        List<T> result = jpaAccess.findByPagination(query, pageNo, pageSize, entityClass);
        return result;
    }

    public List<T> findByPaginationUsingNamedQuery(String nameOfQuery, int pageNo, int pageSize) {
        List<T> result = jpaAccess.findByPaginationUsingNamedQuery(nameOfQuery, pageNo, pageSize, entityClass);
        return result;
    }

    public T findOneUsingNamedQuery(String nameOfquery) {
        return jpaAccess.findOneUsingNamedQuery(nameOfquery, entityClass);
    }

    public void executeUpdateUsingNamedQuery(String nameOfQuery) {
        jpaAccess.executeUpdateUsingNamedQuery(nameOfQuery);
    }

    public <T> void bulkUpdate(T[] entities) {
        jpaAccess.bulkUpdate(entities);
    }
}
