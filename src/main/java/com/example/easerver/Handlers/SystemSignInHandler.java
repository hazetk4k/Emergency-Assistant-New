package com.example.easerver.Handlers;

import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.IMPL.SystemUserDAOImpl;
import com.example.easerver.Entities.SystUserEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.SystemUser;
import com.example.easerver.Services.UserAuthenticator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;

public class SystemSignInHandler extends PostHandler {

    private final SystemUserDAO systemUserDAO;

    private SystUserEntity systUserEntity;

    public SystemSignInHandler() {
        this.systemUserDAO = new SystemUserDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostRequest(String output) {
        try {
            SystemUser systemUser = gson.fromJson(output, SystemUser.class);
            System.out.println("Получены данные:" + systemUser.getLogin() + " " + systemUser.getPassword());

            if (systemUser.getLogin() == null || systemUser.getPassword() == null) {
                return 400;
            }

            try {
                systUserEntity = systemUserDAO.findByUserLogin(systemUser.getLogin());

                if (systUserEntity == null) {
                    return 404;
                }
            } catch (Exception e) {
                System.out.println("Ошибка при поиске пользователя: " + e.getMessage());
                return 404;
            }

            return UserAuthenticator.authenticateUser(systUserEntity.getPassword(), systemUser.getPassword());

        } catch (JsonSyntaxException e) {
            System.out.println("Некорректный формат JSON: " + e.getMessage());
            return 400; // Bad Request
        } catch (Exception e) {
            System.out.println("Ошибка при обработке запроса: " + e.getMessage());
            return 500; // Internal Server Error
        }
    }


    @Override
    protected boolean shouldReturnResponseBody(String requestBody) {
        return true;
    }

    @Override
    protected String generateResponseBody(String requestBody) {
        Map<String, Integer> jsonResponse = new HashMap<>();
        byte userStatus = systUserEntity.getStatusSyst();
        int user_Id = systUserEntity.getIdSyst();
        jsonResponse.put("status", (int) userStatus);
        System.out.println(user_Id);
        jsonResponse.put("user_id", user_Id);
        return gson.toJson(jsonResponse);
    }
}
