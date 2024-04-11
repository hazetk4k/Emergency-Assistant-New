package com.example.easerver.Handlers.AdminHandlers.KindSettings;

import com.example.easerver.DBTransactions.DAO.CharEmDAO;
import com.example.easerver.DBTransactions.IMPL.CharEmDAOImpl;
import com.example.easerver.Entities.CharEmEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetUpChars extends GetHandler {

    private final CharEmDAO charEmDAO;

    public SetUpChars(){
        this.charEmDAO = new CharEmDAOImpl();
    }
    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> namesList = new ArrayList<>();
        try {
            List<CharEmEntity> listOfChars = charEmDAO.findAll();
            Gson gson = new Gson();
            for (CharEmEntity charEntity : listOfChars) {
                namesList.add(charEntity.getCharName());
            }
            return gson.toJson(namesList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
