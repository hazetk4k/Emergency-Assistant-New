package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.CharEmDAO;
import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Entities.CharEmEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class CharEmDAOImpl extends GenericDAOImpl<CharEmEntity, Integer> implements CharEmDAO {
    public CharEmDAOImpl() {
        super(CharEmEntity.class);
    }

    @Override
    public int getCharIdByName(String name) {
        try (EntityManager entityManager = EntityManagerUtil.getEntityManager()) {
            TypedQuery<Integer> query = entityManager.createQuery("SELECT k.id FROM CharEmEntity k WHERE k.charName = :name", Integer.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Характер ЧС с именем " + name + " не найден.");
            return -1;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске характера ЧС по имени: " + e.getMessage());
            return -2;
        }
    }
}
