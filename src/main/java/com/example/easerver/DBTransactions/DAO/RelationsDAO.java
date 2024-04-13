package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.ServiceKindRelationEntity;

import java.util.List;

public interface RelationsDAO extends GenericDAO<ServiceKindRelationEntity, Integer>{
    List<ServiceKindRelationEntity> getRelationsByKindID(int id);

    void deleteByKindAndService(int kindId, int serviceId);

    void deleteByKindId(int kindId);

    boolean existsByKindAndService(int kindId, int serviceId);
}
