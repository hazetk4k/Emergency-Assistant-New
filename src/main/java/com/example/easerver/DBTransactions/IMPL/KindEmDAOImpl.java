package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.KindEmEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class KindEmDAOImpl extends GenericDAOImpl<KindEmEntity, Integer> implements KindEmDAO {
    public KindEmDAOImpl() {
        super(KindEmEntity.class);
    }

    @Override
    public int getKindIdByName(String name) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            TypedQuery<Integer> query = entityManager.createQuery("SELECT k.id FROM KindEmEntity k WHERE k.kindName = :name", Integer.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Вид ЧС с именем " + name + " не найден.");
            return -1;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске характера ЧС по имени: " + e.getMessage());
            return -2;
        }
    }
}
