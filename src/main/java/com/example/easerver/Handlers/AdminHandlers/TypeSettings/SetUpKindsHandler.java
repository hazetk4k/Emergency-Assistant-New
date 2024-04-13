package com.example.easerver.Handlers.AdminHandlers.TypeSettings;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetUpKindsHandler extends GetHandler {

    private final KindEmDAO kindEmDAO;

    public SetUpKindsHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> namesList = new ArrayList<>();
        try {
            List<KindEmEntity> listOfKinds = kindEmDAO.findAll();
            Gson gson = new Gson();
            for (KindEmEntity kindEntity : listOfKinds) {
                namesList.add(kindEntity.getKindName());
            }
            return gson.toJson(namesList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
