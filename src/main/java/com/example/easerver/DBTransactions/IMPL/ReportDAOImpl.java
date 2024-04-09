package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.ReportDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.ReportsEntity;
import com.example.easerver.Entities.UserDataEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportDAOImpl extends GenericDAOImpl<ReportsEntity, Integer> implements ReportDAO{

    public ReportDAOImpl() {
        super(ReportsEntity.class);
    }

    @Override
    public List<ReportsEntity> getReportsByUserEmail(String email) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            String jpql = "SELECT r FROM ReportsEntity r WHERE r.userEmail = :email";
            TypedQuery<ReportsEntity> query = entityManager.createQuery(jpql, ReportsEntity.class);
            query.setParameter("email", email);
            return query.getResultList();
        } catch (NoResultException e) {
            System.out.println("Заявления от пользователя " + email + " не найдены.");
            return Collections.emptyList();
        } catch (Exception e) {
            System.out.println("Ошибка при поиске заявлений по email: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
