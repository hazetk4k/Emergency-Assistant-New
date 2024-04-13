package com.example.easerver.Handlers.AdminHandlers.TypeSettings;

import com.example.easerver.DBTransactions.DAO.TypeEmDAO;
import com.example.easerver.DBTransactions.IMPL.TypeEmDAOImpl;
import com.example.easerver.Entities.TypeEmEntity;
import com.example.easerver.Handlers.BaseHandlers.DeleteHandler;

import java.util.Map;
import java.util.Objects;

public class DeleteTypeHandler extends DeleteHandler {
    private final TypeEmDAO typeEmDAO;

    public DeleteTypeHandler() {
        this.typeEmDAO = new TypeEmDAOImpl();
    }

    @Override
    protected int handleDeleteRequest(Map<String, String> params) {
        try {
            if (!params.containsKey("type_name")) {
                return 400;
            }
            String type_name;
            try {
                type_name = params.get("type_name");
            } catch (NumberFormatException e) {
                return 400;
            }

            TypeEmEntity typeEmEntity = typeEmDAO.findByTypeName(type_name);
            if (Objects.equals(typeEmEntity, null)) return 404;

            try {

                typeEmDAO.delete(typeEmEntity);
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
