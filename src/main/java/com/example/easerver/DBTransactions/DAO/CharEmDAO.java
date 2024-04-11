package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.CharEmEntity;

public interface CharEmDAO extends GenericDAO<CharEmEntity, Integer> {

    int getCharIdByName(String name);
}
