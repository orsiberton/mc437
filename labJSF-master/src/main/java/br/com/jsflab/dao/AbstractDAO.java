package br.com.jsflab.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDAO<T, K extends Serializable> implements DAO<T, K> {

    private static final long serialVersionUID = 4390975362917625589L;

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {

        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public T buscar(Long id) {

        return entityManager.find(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> buscarTodos() {

        return createQuery("from " + clazz.getName()).getResultList();
    }

    @Override
    public Long count() {

        return (Long) createQuery("select count(*) from " + clazz.getName()).getSingleResult();
    }

    @Override
    public void salvar(T entidade) {

        entityManager.persist(entidade);
    }

    @Override
    public void atualizar(T entidade) {

        entityManager.merge(entidade);
    }

    @Override
    public void remover(T entidade) {

        entityManager.remove(entidade);
    }

    @Override
    public void remover(Long id) {

        T entidade = buscar(id);
        remover(entidade);
    }

    public void setClazz(Class<T> clazz) {

        this.clazz = clazz;
    }

    public Query createQuery(String query) {

        return entityManager.createQuery(query);
    }

}
