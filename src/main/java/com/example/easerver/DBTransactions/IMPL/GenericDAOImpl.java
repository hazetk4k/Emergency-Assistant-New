package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.GenericDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    @Override
    public T findById(ID id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
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
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void delete(T entity) {

    }
}