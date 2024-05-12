package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.DistrictsEntity;

public interface DistrictsDAO extends GenericDAO<DistrictsEntity, Integer>{
    Integer findByName(String district_name);
}
