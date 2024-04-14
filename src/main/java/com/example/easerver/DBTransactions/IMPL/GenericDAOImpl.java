package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.GenericDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    private final Class<T> entityClass;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T findById(ID id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            return entityManager.find(entityClass, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            List<T> resultList;
            try {
                transaction.begin();
                String queryString = "SELECT e FROM " + entityClass.getSimpleName() + " e";
                TypedQuery<T> query = entityManager.createQuery(queryString, entityClass);
                resultList = query.getResultList();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                throw new RuntimeException("Ошибка при удалении сущности из базы данных", e);
            }
            return resultList;
        }
    }

    @Override
    public void save(T entity) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(entity);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                throw new RuntimeException("Ошибка при удалении сущности из базы данных", e);
            }
        }
    }

    @Override
    public void delete(T entity) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                throw new RuntimeException("Ошибка при удалении сущности из базы данных", e);
            }
        }
    }
}