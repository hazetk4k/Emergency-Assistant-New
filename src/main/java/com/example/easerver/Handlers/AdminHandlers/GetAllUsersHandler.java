package com.example.easerver.Handlers.AdminHandlers;

import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.IMPL.SystemUserDAOImpl;
import com.example.easerver.Entities.SystUserEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUsersHandler extends GetHandler {

    private final SystemUserDAO systemUserDAO;

    public GetAllUsersHandler() {
        this.systemUserDAO = new SystemUserDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> usersList = new ArrayList<>();
        try {
            List<SystUserEntity> listOfUsers = systemUserDAO.findAll();
            Gson gson = new Gson();
            for (SystUserEntity user : listOfUsers) {
                Map<String, Object> usersMap = new HashMap<>();

                usersMap.put("id_syst", user.getIdSyst());
                usersMap.put("login_syst", user.getLoginSyst());
                usersMap.put("status_syst", user.getStatusSyst());

                usersList.add(usersMap);
            }

            return gson.toJson(usersList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
