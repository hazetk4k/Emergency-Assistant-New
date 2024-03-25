package com.example.easerver.DBTransactions.DAO;


import jakarta.persistence.Entity;

import java.util.List;

public interface GenericDAO<T, ID> {
    T findById(ID id);

    List<T> findAll();

    void save(T entity);

    void delete(T entity);
}
