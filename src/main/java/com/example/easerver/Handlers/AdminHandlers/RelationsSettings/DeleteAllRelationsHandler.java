package com.example.easerver.Handlers.AdminHandlers.RelationsSettings;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.DAO.RelationsDAO;
import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.RelationsDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Handlers.BaseHandlers.DeleteHandler;

import java.util.Map;

public class DeleteAllRelationsHandler extends DeleteHandler {

    private final RelationsDAO relationsDAO;
    private final ServicesDAO servicesDAO;
    private final KindEmDAO kindEmDAO;

    public DeleteAllRelationsHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
        this.relationsDAO = new RelationsDAOImpl();
        this.servicesDAO = new ServicesDAOImpl();
    }

    @Override
    protected int handleDeleteRequest(Map<String, String> params) {
        try {
            if (!params.containsKey("kind_name")) {
                return 400;
            }
            String kind_name;
            try {
                kind_name = params.get("kind_name");
            } catch (NumberFormatException e) {
                return 400;
            }

            int kind_id = kindEmDAO.getKindIdByName(kind_name);

            try {
                relationsDAO.deleteByKindId(kind_id);
                System.out.println("Все прошло");
                return 200;

            } catch (Exception e) {
                System.out.println("Ошибка при удалении: " + e.getMessage());
                return 409;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при обработке запроса: " + e.getMessage());
            return 500;
        }
    }
}
