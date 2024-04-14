package com.example.easerver.Handlers.AdminHandlers.KindSettings;

import com.example.easerver.DBTransactions.DAO.CharEmDAO;
import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.IMPL.CharEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.AdminSettingsModels.NewKindEm;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddNewKindHandler extends PostHandler {

    private final KindEmDAO kindEmDAO;
    private final CharEmDAO charEmDAO;

    public AddNewKindHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
        this.charEmDAO = new CharEmDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            NewKindEm newKindEm = gson.fromJson(requestBody, NewKindEm.class);
            KindEmEntity kindEmEntity = new KindEmEntity();

            if(kindEmDAO.getKindIdByName(newKindEm.getKind_name()) != -1){
                return 409;
            }

            kindEmEntity.setKindName(newKindEm.getKind_name());
            int char_id = charEmDAO.getCharIdByName(newKindEm.getChar_name());
            kindEmEntity.setIdChar(char_id);

            kindEmDAO.save(kindEmEntity);
            return 200;
        } catch (Exception e) {
            return 500;
        }
    }
}
