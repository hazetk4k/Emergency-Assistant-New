package com.example.easerver.Handlers.AdminHandlers.RelationsSettings;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.DAO.RelationsDAO;
import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.RelationsDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Entities.ServiceKindRelationEntity;
import com.example.easerver.Entities.TypeEmEntity;
import com.example.easerver.Handlers.BaseHandlers.DeleteHandler;

import java.util.Map;
import java.util.Objects;

public class DeleteServiceRelationHandler extends DeleteHandler {

    private final RelationsDAO relationsDAO;
    private final ServicesDAO servicesDAO;
    private final KindEmDAO kindEmDAO;

    public DeleteServiceRelationHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
        this.relationsDAO = new RelationsDAOImpl();
        this.servicesDAO = new ServicesDAOImpl();
    }

    @Override
    protected int handleDeleteRequest(Map<String, String> params) {
        try {
            if (!params.containsKey("kind_name") || !params.containsKey("service_name")) {
                return 400;
            }

            String kindName = params.get("kind_name");
            String serviceName = params.get("service_name");

            int kind_id = kindEmDAO.getKindIdByName(kindName);
            int service_id = servicesDAO.findServiceIdByName(serviceName);

            try {
                relationsDAO.deleteByKindAndService(kind_id, service_id);
                System.out.println("Связи успешно удалены");
                return 200;

            } catch (Exception e) {
                System.out.println("Ошибка при удалении связей: " + e.getMessage());
                return 409;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при обработке запроса: " + e.getMessage());
            return 500;
        }

    }
}
