package com.example.easerver.Handlers.AdminHandlers.UserSettings;

import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.IMPL.SystemUserDAOImpl;
import com.example.easerver.Entities.SystUserEntity;
import com.example.easerver.Handlers.BaseHandlers.PutHandler;
import com.example.easerver.Models.AdminSettingsModels.SystemUserStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UpdateUserStatus extends PutHandler {

    private final SystemUserDAO systemUserDAO;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public UpdateUserStatus() {
        this.systemUserDAO = new SystemUserDAOImpl();
    }

    @Override
    protected int handlePutRequest(String requestBody) {
        SystemUserStatus systemUserStatus = gson.fromJson(requestBody, SystemUserStatus.class);
        SystUserEntity systUserEntity = systemUserDAO.findByUserLogin(systemUserStatus.getLogin());
        if(systemUserStatus.getId() == systUserEntity.getIdSyst()){
            return 403;
        }
        try {
            systUserEntity = systemUserDAO.findById(systemUserStatus.getId());
            if (systUserEntity == null) {
                return 404;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при поиске пользователя: " + e.getMessage());
            return 404;
        }
        systemUserDAO.updateUserStatus(systemUserStatus.getId(), systemUserStatus.getStatus());
        System.out.println("Все прошло");
        return 200;
    }
}
