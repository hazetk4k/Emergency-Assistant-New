package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.DispChoiceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

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

    @Override
    public void confirmChosenServices(DispChoiceEntity dispChoice, String services, Timestamp timestamp) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();
            dispChoice.setServices(services);
            dispChoice.setCallServicesTime(timestamp);
            dispChoice.setStage("2");
            entityManager.merge(dispChoice);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void confirmReceivedData(DispChoiceEntity dispChoice, Timestamp timestamp, int people_amount, int died_amount) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();
            dispChoice.setReceiveDataTime(timestamp);
            dispChoice.setDiedAmount(died_amount);
            dispChoice.setPeopleAmount(people_amount);
            dispChoice.setStage("3");
            entityManager.merge(dispChoice);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void confirmAdditionalServices(DispChoiceEntity dispChoice, Timestamp timestamp, String services) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();
            dispChoice.setAdditionalServices(services);
            dispChoice.setAdditionalServicesTime(timestamp);
            dispChoice.setStage("4");
            entityManager.merge(dispChoice);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void confirmEndActionsTime(DispChoiceEntity dispChoice, Timestamp timestamp) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            entityManager.getTransaction().begin();
            dispChoice.setEndActionsTime(timestamp);
            dispChoice.setStage("5");
            entityManager.merge(dispChoice);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<DispChoiceEntity> findAllChoicesByStage(String stage) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            String jpql = "SELECT r FROM DispChoiceEntity r WHERE r.stage = :stage";
            TypedQuery<DispChoiceEntity> query = entityManager.createQuery(jpql, DispChoiceEntity.class);
            query.setParameter("stage", stage);
            return query.getResultList();
        } catch (NoResultException e) {
            System.out.println("На данный момент нет заявлений на стадии обработки " + stage + ".");
            return Collections.emptyList();
        } catch (Exception e) {
            System.out.println("Ошибка при поиске результатов работы диспетчера на стадии: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
