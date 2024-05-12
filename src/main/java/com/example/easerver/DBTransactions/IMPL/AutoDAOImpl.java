package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.AutoDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.AutoEntity;
import com.example.easerver.Entities.TypeEmEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class AutoDAOImpl extends GenericDAOImpl<AutoEntity, Integer> implements AutoDAO {
    public AutoDAOImpl() {
        super(AutoEntity.class);
    }

    @Override
    public List<AutoEntity> getAutoByServiceAndDistrict(int service_id, int district_id) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            String jpql = "SELECT r FROM AutoEntity r WHERE r.districtsByDistrictId.id = :district_id and r.serviceByServiceId.id = :service_id";
            TypedQuery<AutoEntity> query = entityManager.createQuery(jpql, AutoEntity.class);
            query.setParameter("district_id", district_id);
            query.setParameter("service_id", service_id);
            return query.getResultList();
        } catch (NoResultException e) {
            System.out.println("Транспорт по id службы " + service_id + " и id района " + district_id + " не найден.");
            return Collections.emptyList();
        } catch (Exception e) {
            System.out.println("Ошибка при поиске транспорта служб:" + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public AutoEntity findByNumber(String autoNum) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            TypedQuery<AutoEntity> query = entityManager.createQuery(
                    "SELECT u FROM AutoEntity u WHERE u.autoNum = :autoNum ", AutoEntity.class);
            query.setParameter("autoNum", autoNum);
            System.out.println(autoNum);

            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Авто с номером " + autoNum + " не найден.");
            return null;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске авто по номеру: " + e.getMessage());
            return null;
        }
    }
}
