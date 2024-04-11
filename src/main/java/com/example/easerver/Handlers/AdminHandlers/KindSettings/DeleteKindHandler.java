package com.example.easerver.Handlers.AdminHandlers.KindSettings;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Entities.SystUserEntity;
import com.example.easerver.Handlers.BaseHandlers.DeleteHandler;

import java.util.Map;
import java.util.Objects;

public class DeleteKindHandler extends DeleteHandler {

    private final KindEmDAO kindEmDAO;

    public DeleteKindHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
    }

    @Override
    protected int handleDeleteRequest(Map<String, String> params) {
        try {
            if (!params.containsKey("kind_id")) {
                return 400;
            }
            int kind_id;
            try {
                kind_id = Integer.parseInt(params.get("kind_id"));
            } catch (NumberFormatException e) {
                return 400;
            }

            KindEmEntity kindEmEntity = kindEmDAO.findById(kind_id);
            if (Objects.equals(kindEmEntity, null)) return 404;

            try {
                kindEmDAO.delete(kindEmEntity);
                System.out.println("Все прошло");
                return 200;
            } catch (Exception e) {
                System.out.println("Ошибка при удалении: " + e.getMessage());
                return 500;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при обработке запроса: " + e.getMessage());
            return 500;
        }
    }
}

