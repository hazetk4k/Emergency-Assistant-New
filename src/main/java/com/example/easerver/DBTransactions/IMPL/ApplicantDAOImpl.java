package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.UserDataEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ApplicantDAOImpl extends GenericDAOImpl<UserDataEntity, Integer> implements ApplicantDAO {

    @Override
    public UserDataEntity findByEmail(String email) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            TypedQuery<UserDataEntity> query = entityManager.createQuery(
                    "SELECT u FROM UserDataEntity u WHERE u.email = :email ",
                    UserDataEntity.class);
            query.setParameter("email", email);

            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Пользователь с email " + email + " не найден.");
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске пользователя по email: " + e.getMessage());
            return null;
        }
    }
}
