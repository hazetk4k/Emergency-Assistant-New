package com.example.easerver.Handlers.DispatcherHandlers;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.DAO.RelationsDAO;
import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.RelationsDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Entities.ServiceEntity;
import com.example.easerver.Entities.ServiceKindRelationEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetServicesByKind extends GetHandler {

    private final ServicesDAO servicesDAO;
    private final KindEmDAO kindEmDAO;

    private final RelationsDAO relationsDAO;
    Gson gson = new Gson();

    public GetServicesByKind() {
        this.servicesDAO = new ServicesDAOImpl();
        this.kindEmDAO = new KindEmDAOImpl();
        this.relationsDAO = new RelationsDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<String> servicesNames = new ArrayList<>();
        try {
            int kind_id = kindEmDAO.getKindIdByName(params.get("kind"));
            List<ServiceKindRelationEntity> relations = relationsDAO.findAll();
            for (ServiceKindRelationEntity relation : relations) {
                if (relation.getKindId() == kind_id) {
                    ServiceEntity service = servicesDAO.findById(relation.getServiceId());
                    servicesNames.add(service.getServiceName());
                }
            }
            return gson.toJson(servicesNames);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки сервисов реагирования ЧС", e);
        }
    }
}
