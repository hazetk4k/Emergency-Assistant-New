package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.TypeEmEntity;

public interface TypeEmDAO extends GenericDAO<TypeEmEntity, Integer>{

    TypeEmEntity findByTypeName(String name);
}
