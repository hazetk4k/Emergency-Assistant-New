package com.example.easerver.Handlers.AdminHandlers.RelationsSettings;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.DAO.RelationsDAO;
import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.RelationsDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Entities.ServiceKindRelationEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.AdminSettingsModels.NewRelation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddNewRelationHandler extends PostHandler {
    private final RelationsDAO relationsDAO;
    private final ServicesDAO servicesDAO;

    private final KindEmDAO kindEmDAO;

    public AddNewRelationHandler() {
        this.relationsDAO = new RelationsDAOImpl();
        this.kindEmDAO = new KindEmDAOImpl();
        this.servicesDAO = new ServicesDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            NewRelation newRelation = gson.fromJson(requestBody, NewRelation.class);
            int kindId = kindEmDAO.getKindIdByName(newRelation.getKind_name());
            int serviceId = servicesDAO.findServiceIdByName(newRelation.getService_name());

            if (relationsDAO.existsByKindAndService(kindId, serviceId)) {
                return 409;
            }

            ServiceKindRelationEntity relationEntity = new ServiceKindRelationEntity();

            relationEntity.setKindId(kindId);
            relationEntity.setServiceId(serviceId);

            relationsDAO.save(relationEntity);
            return 200;
        } catch (Exception e) {
            return 500;
        }
    }
}
