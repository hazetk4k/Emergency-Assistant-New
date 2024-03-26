package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.DBTransactions.DAO.TypeEmDAO;
import com.example.easerver.DBTransactions.IMPL.TypeEmDAOImpl;
import com.example.easerver.Entities.TypeEmEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.example.easerver.Models.AdminSettingsModels.TypesEm;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class EmergencyTypesHandler extends GetHandler {
    private final TypeEmDAO typeEmDAO;

    public EmergencyTypesHandler() {
        this.typeEmDAO = new TypeEmDAOImpl();
    }

    @Override
    public String handleGet(Map<String, String> params) {
        try {
            List<TypeEmEntity> list = typeEmDAO.findAll();
            Gson gson = new Gson();

            List<TypesEm> types = list.stream()
                    .map(i -> new TypesEm(i.getName(), i.getRecommendations())).collect(Collectors.toList());

            return gson.toJson(types);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}