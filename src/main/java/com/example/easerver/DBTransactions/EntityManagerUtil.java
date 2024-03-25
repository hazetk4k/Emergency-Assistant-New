package com.example.easerver.DBTransactions;

import jakarta.persistence.*;

public class EntityManagerUtil {
    private static final String PERSISTENCE_UNIT_NAME = "default";

    private static EntityManagerFactory entityManagerFactory;

    private EntityManagerUtil() {

    }

    private static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
