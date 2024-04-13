package com.example.easerver.Handlers.AdminHandlers.TypeSettings;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.DAO.TypeEmDAO;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.TypeEmDAOImpl;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Entities.TypeEmEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.AdminSettingsModels.NewKindEm;
import com.example.easerver.Models.AdminSettingsModels.NewTypeEm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddNewTypeHandler extends PostHandler {
    private final KindEmDAO kindEmDAO;
    private final TypeEmDAO typeEmDAO;

    public AddNewTypeHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
        this.typeEmDAO = new TypeEmDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            NewTypeEm newKindEm = gson.fromJson(requestBody, NewTypeEm.class);
            TypeEmEntity typeEmEntity = new TypeEmEntity();

            typeEmEntity.setName(newKindEm.getType_name());
            typeEmEntity.setRecommendations(newKindEm.getRecommendations());
            int kind_id = kindEmDAO.getKindIdByName(newKindEm.getKind_name());
            typeEmEntity.setIdKind(kind_id);

            typeEmDAO.save(typeEmEntity);
            return 200;
        } catch (Exception e) {
            return 500;
        }
    }
}
