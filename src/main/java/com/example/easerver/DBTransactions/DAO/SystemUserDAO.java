package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.SystUserEntity;

public interface SystemUserDAO extends GenericDAO<SystUserEntity, Integer>{
    SystUserEntity findByUserLogin(String login);

    void updateUserStatus(Integer user_id, byte status);
}
