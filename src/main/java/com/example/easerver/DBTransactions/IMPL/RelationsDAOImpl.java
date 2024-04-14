package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.RelationsDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.ServiceKindRelationEntity;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

public class RelationsDAOImpl extends GenericDAOImpl<ServiceKindRelationEntity, Integer> implements RelationsDAO {
    public RelationsDAOImpl() {
        super(ServiceKindRelationEntity.class);
    }

    @Override
    public List<ServiceKindRelationEntity> getRelationsByKindID(int id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            String jpql = "SELECT r FROM ServiceKindRelationEntity r WHERE r.kindId = :id";
            TypedQuery<ServiceKindRelationEntity> query = entityManager.createQuery(jpql, ServiceKindRelationEntity.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NoResultException e) {
            System.out.println("У вида с id " + id + " не найдены сущности.");
            return Collections.emptyList();
        } catch (Exception e) {
            System.out.println("Ошибка при поиске заявлений по email: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteByKindAndService(int kindId, int serviceId) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                Query query = entityManager.createQuery("DELETE FROM ServiceKindRelationEntity r WHERE r.kindId = :kindId AND r.serviceId = :serviceId");
                query.setParameter("kindId", kindId);
                query.setParameter("serviceId", serviceId);
                query.executeUpdate();

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
    public void deleteByKindId(int kindId) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            List<ServiceKindRelationEntity> relations;
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                TypedQuery<ServiceKindRelationEntity> query = entityManager.createQuery("SELECT k FROM ServiceKindRelationEntity k WHERE k.kindId = :kindId", ServiceKindRelationEntity.class);
                query.setParameter("kindId", kindId);
                relations = query.getResultList();
                for (ServiceKindRelationEntity relation : relations) {
                    entityManager.remove(relation);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                throw new RuntimeException("Ошибка при удалении связей из базы данных", e);
            }
        }
    }

    @Override
    public boolean existsByKindAndService(int kindId, int serviceId) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            Long count = (Long) entityManager.createQuery(
                            "SELECT COUNT(r) FROM ServiceKindRelationEntity r WHERE r.kindId = :kindId AND r.serviceId = :serviceId")
                    .setParameter("kindId", kindId)
                    .setParameter("serviceId", serviceId)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при проверке существования связи в базе данных", e);
        }
    }
}
