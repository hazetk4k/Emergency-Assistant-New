package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.TypeEmDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.TypeEmEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TypeEmDAOImpl extends GenericDAOImpl<TypeEmEntity, Integer> implements TypeEmDAO {

    @Override
    public List<TypeEmEntity> findAll() {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            List<TypeEmEntity> list = null;
            try {
                transaction.begin();
                TypedQuery<TypeEmEntity> q = entityManager.createQuery("SELECT e from TypeEmEntity e", TypeEmEntity.class);
                list = q.getResultList();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                System.out.println(e.getMessage());
            }
            return list;
        }
    }
}
