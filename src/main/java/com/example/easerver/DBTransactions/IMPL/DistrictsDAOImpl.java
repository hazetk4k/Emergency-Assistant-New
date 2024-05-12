package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.DistrictsDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.DistrictsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class DistrictsDAOImpl extends GenericDAOImpl<DistrictsEntity, Integer> implements DistrictsDAO {

    public DistrictsDAOImpl() {
        super(DistrictsEntity.class);
    }

    @Override
    public Integer findByName(String district_name) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            TypedQuery<Integer> query = entityManager.createQuery("SELECT k.id FROM DistrictsEntity k WHERE k.districtName = :name", Integer.class);
            query.setParameter("name", district_name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Район с именем " + district_name + " не найден.");
            return -1;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске района по имени: " + e.getMessage());
            return -2;
        }
    }
}
