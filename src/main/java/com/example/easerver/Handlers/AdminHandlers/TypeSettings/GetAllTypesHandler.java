package com.example.easerver.Handlers.AdminHandlers.TypeSettings;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.DAO.TypeEmDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.TypeEmDAOImpl;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Entities.TypeEmEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllTypesHandler extends GetHandler {
    private final TypeEmDAO typeEmDAO;
    private final KindEmDAO kindEmDAO;

    public GetAllTypesHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
        this.typeEmDAO = new TypeEmDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> typeList = new ArrayList<>();
        try {
            List<TypeEmEntity> listOfUsers = typeEmDAO.findAll();
            Gson gson = new Gson();
            for (TypeEmEntity typeEm : listOfUsers) {
                Map<String, Object> typesMap = new HashMap<>();

                typesMap.put("type", typeEm.getName());
                typesMap.put("recommendations", typeEm.getRecommendations());
                KindEmEntity kindEm = kindEmDAO.findById(typeEm.getIdKind());
                typesMap.put("kind", kindEm.getKindName());

                typeList.add(typesMap);
            }

            return gson.toJson(typeList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
