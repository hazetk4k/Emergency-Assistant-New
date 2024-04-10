package com.example.easerver.Handlers.AdminHandlers;

import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.IMPL.SystemUserDAOImpl;
import com.example.easerver.Handlers.BaseHandlers.PutHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UpdateUserStatus extends PutHandler {

    private final SystemUserDAO systemUserDAO;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public UpdateUserStatus() {
        this.systemUserDAO = new SystemUserDAOImpl();
    }
//TODO:Доделать это...
    @Override
    protected int handlePutRequest(String requestBody) {

        return 0;
    }
}
