package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.SystUserEntity;
import com.example.easerver.Entities.UserDataEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class SystemUserDAOImpl extends GenericDAOImpl<SystUserEntity, Integer> implements SystemUserDAO {
    public SystemUserDAOImpl() {
        super(SystUserEntity.class);
    }

    @Override
    public SystUserEntity findByUserLogin(String login) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            TypedQuery<SystUserEntity> query = entityManager.createQuery(
                    "SELECT u FROM SystUserEntity u WHERE u.loginSyst = :login ", SystUserEntity.class);
            query.setParameter("login", login);

            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Пользователь с логином " + login + " не найден.");
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске пользователя по логину: " + e.getMessage());
            return null;
        }
    }
}
