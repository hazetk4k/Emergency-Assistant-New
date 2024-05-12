package com.example.easerver.Handlers.AdminHandlers.DistrictsSettings;

import com.example.easerver.DBTransactions.DAO.DistrictsDAO;
import com.example.easerver.DBTransactions.IMPL.DistrictsDAOImpl;
import com.example.easerver.Entities.DistrictsEntity;
import com.example.easerver.Handlers.BaseHandlers.DeleteHandler;
import java.util.Map;
import java.util.Objects;

public class DeleteDistrictHandler extends DeleteHandler {
    private final DistrictsDAO districtsDAO;

    public DeleteDistrictHandler() {
        this.districtsDAO = new DistrictsDAOImpl();
    }

    @Override
    protected int handleDeleteRequest(Map<String, String> params) {
        try {
            if (!params.containsKey("district_name")) {
                return 400;
            }
            String district_Name;
            try {
                district_Name = params.get("district_name");
            } catch (NumberFormatException e) {
                return 400;
            }

            int districts_id = districtsDAO.findByName(district_Name);
            if (Objects.equals(districts_id, -1)) return 404;

            try {
                districtsDAO.delete(districtsDAO.findById(districts_id));
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
