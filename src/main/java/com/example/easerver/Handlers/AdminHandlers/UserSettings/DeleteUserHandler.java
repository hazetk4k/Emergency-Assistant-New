package com.example.easerver.Handlers.AdminHandlers.UserSettings;

import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.IMPL.SystemUserDAOImpl;
import com.example.easerver.Entities.SystUserEntity;
import com.example.easerver.Handlers.BaseHandlers.DeleteHandler;

import java.util.Map;

public class DeleteUserHandler extends DeleteHandler {
    private final SystemUserDAO systemUserDAO;

    public DeleteUserHandler() {
        this.systemUserDAO = new SystemUserDAOImpl();
    }

    @Override
    protected int handleDeleteRequest(Map<String, String> params) {
        try {
            if (!params.containsKey("user_id")) {
                return 400;
            }
            int id;
            try {
                id = Integer.parseInt(params.get("user_id"));
            } catch (NumberFormatException e) {
                return 400;
            }

            SystUserEntity systUserEntity = systemUserDAO.findById(id);
            if (systUserEntity == null) {
                return 404;
            }

            try {
                systemUserDAO.delete(systUserEntity);
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
