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
    protected int handlePostInput(String output) {
        try {
            SystemUser systemUser = gson.fromJson(output, SystemUser.class);
            System.out.println("======================");
            System.out.println("Получены данные:" + systemUser.getLogin() + " " + systemUser.getPassword());

            // Проверяем, что запрос содержит логин и пароль
            if (systemUser.getLogin() == null || systemUser.getPassword() == null) {
                return 400; // Bad Request
            }

            // Получаем пользователя по логину
            try {
                systUserEntity = systemUserDAO.findByUserLogin(systemUser.getLogin());

                // Если пользователь не найден, вернуть код 404 (Not Found)
                if (systUserEntity == null) {
                    return 404; // Not Found
                }
            } catch (Exception e) {
                // В случае возникновения исключения при поиске пользователя, возвращаем код 404 (Not Found)
                System.out.println("Ошибка при поиске пользователя: " + e.getMessage());
                return 404; // Not Found
            }

            // Авторизуем пользователя
            return UserAuthenticator.authenticateUser(systUserEntity.getPassword(), systemUser.getPassword());

        } catch (JsonSyntaxException e) {
            // Некорректный формат JSON в теле запроса
            System.out.println("Некорректный формат JSON: " + e.getMessage());
            return 400; // Bad Request
        } catch (Exception e) {
            // Возникла ошибка при обработке запроса
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
        jsonResponse.put("status", (int) userStatus);
        return gson.toJson(jsonResponse);
    }
}
