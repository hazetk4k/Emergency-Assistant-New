package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.UserDataEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ApplicantDAOImpl implements ApplicantDAO {

    private EntityManager entityManager;

    public ApplicantDAOImpl() {
        this.entityManager = EntityManagerUtil.getEntityManager();
    }

    @Override
    public UserDataEntity findById(Integer integer) {
        return null;
    }

    @Override
    public List<UserDataEntity> findAll() {
        return null;
    }

    @Override
    public void save(UserDataEntity entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(entity);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(UserDataEntity entity) {

    }
}
