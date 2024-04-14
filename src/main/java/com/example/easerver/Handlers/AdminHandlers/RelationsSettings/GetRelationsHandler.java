package com.example.easerver.Handlers.AdminHandlers.RelationsSettings;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.DAO.RelationsDAO;
import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.RelationsDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Entities.ServiceEntity;
import com.example.easerver.Entities.ServiceKindRelationEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRelationsHandler extends GetHandler {
    private final ServicesDAO servicesDAO;
    private final KindEmDAO kindEmDAO;
    private final RelationsDAO relationsDAO;

    public GetRelationsHandler() {
        this.servicesDAO = new ServicesDAOImpl();
        this.kindEmDAO = new KindEmDAOImpl();
        this.relationsDAO = new RelationsDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<KindEmEntity> kindEmEntities = kindEmDAO.findAll();
        List<ServiceKindRelationEntity> relationEntities = relationsDAO.findAll();

        Map<String, List<String>> kindRelationsMap = new HashMap<>();

        for (KindEmEntity kind : kindEmEntities) {
            String kindName = kind.getKindName();
            List<String> serviceNames = new ArrayList<>();

            for (ServiceKindRelationEntity relation : relationEntities) {
                if (relation.getKindId() == kind.getKindId()) {
                    ServiceEntity service = servicesDAO.findById(relation.getServiceId());
                    serviceNames.add(service.getServiceName());
                }
            }

            if (!serviceNames.isEmpty()) {
                kindRelationsMap.put(kindName, serviceNames);
            } else {
                kindRelationsMap.put(kindName, new ArrayList<>());
            }
        }

        Gson gson = new Gson();

        return gson.toJson(kindRelationsMap);
    }
}
