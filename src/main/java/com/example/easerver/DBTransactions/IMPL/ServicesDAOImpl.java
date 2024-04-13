package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.ServiceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ServicesDAOImpl extends GenericDAOImpl<ServiceEntity, Integer> implements ServicesDAO {
    public ServicesDAOImpl() {
        super(ServiceEntity.class);
    }

    @Override
    public int findServiceIdByName(String serviceName) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            TypedQuery<Integer> query = entityManager.createQuery("SELECT k.id FROM ServiceEntity k WHERE k.serviceName = :name", Integer.class);
            query.setParameter("name", serviceName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Вид ЧС с именем " + serviceName + " не найден.");
            return -1;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске характера ЧС по имени: " + e.getMessage());
            return -2;
        }
    }
}
