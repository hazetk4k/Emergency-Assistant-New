package com.example.easerver.Handlers.AdminHandlers.UserSettings;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.IMPL.SystemUserDAOImpl;
import com.example.easerver.Entities.SystUserEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;

import com.example.easerver.Models.SystemUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.security.SecureRandom;

public class AddNewUserHandler extends PostHandler {

    private final SystemUserDAO systemUserDAO;

    public AddNewUserHandler() {
        this.systemUserDAO = new SystemUserDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostRequest(String output) {
        try {
            SystemUser systemUser = gson.fromJson(output, SystemUser.class);
            SystUserEntity systUserEntity = new SystUserEntity();

            if (systemUserDAO.findByUserLogin(systemUser.getLogin()) != null) {
                return 409;
            }

            String hashPassword = BCrypt.with(new SecureRandom()).hashToString(6, systemUser.getPassword().toCharArray());

            systUserEntity.setLoginSyst(systemUser.getLogin());
            systUserEntity.setPassword(hashPassword);
            systUserEntity.setStatusSyst(systemUser.getStatus());

            systemUserDAO.save(systUserEntity);

            return 200;
        } catch (Exception e) {
            return 500;
        }
    }
}
