package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.DispChoiceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class DispChoiceDAOImpl extends GenericDAOImpl<DispChoiceEntity, Integer> implements DispChoiceDAO {
    public DispChoiceDAOImpl() {
        super(DispChoiceEntity.class);
    }

    @Override
    public DispChoiceEntity findByReportId(int report_id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            TypedQuery<DispChoiceEntity> query = entityManager.createQuery(
                    "SELECT u FROM DispChoiceEntity u WHERE u.reportByReportId.id = :report_id ", DispChoiceEntity.class);
            query.setParameter("report_id", report_id);

            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Действия по заявлению " + report_id + " не предприняты.");
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске предпринятых действий: " + e.getMessage());
            return null;
        }
    }
}