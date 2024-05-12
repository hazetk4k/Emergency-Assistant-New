package com.example.easerver.Handlers.AdminHandlers.AutoSettings;

import com.example.easerver.DBTransactions.DAO.AutoDAO;
import com.example.easerver.DBTransactions.IMPL.AutoDAOImpl;
import com.example.easerver.Entities.AutoEntity;
import com.example.easerver.Handlers.BaseHandlers.DeleteHandler;
import java.util.Map;
import java.util.Objects;

public class DeleteAutoHandler extends DeleteHandler {
    private final AutoDAO autoDAO;

    public DeleteAutoHandler(){
        this.autoDAO = new AutoDAOImpl();
    }
    @Override
    protected int handleDeleteRequest(Map<String, String> params) {
        try {
            if (!params.containsKey("auto_id")) {
                return 400;
            }
            int auto_id;
            try {
                auto_id = Integer.parseInt(params.get("auto_id"));
            } catch (NumberFormatException e) {
                return 400;
            }

            AutoEntity auto = autoDAO.findById(auto_id);
            if (Objects.equals(auto, null)) return 404;

            try {
                autoDAO.delete(auto);
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
