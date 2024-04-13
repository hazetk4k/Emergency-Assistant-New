package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.ServiceEntity;

public interface ServicesDAO extends GenericDAO<ServiceEntity, Integer>{
    int findServiceIdByName(String serviceName);
}
