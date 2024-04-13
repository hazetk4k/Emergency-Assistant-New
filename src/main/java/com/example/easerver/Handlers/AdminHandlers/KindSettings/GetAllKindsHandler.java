package com.example.easerver.Handlers.AdminHandlers.KindSettings;

import com.example.easerver.DBTransactions.DAO.CharEmDAO;
import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.IMPL.CharEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.Entities.CharEmEntity;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllKindsHandler extends GetHandler {

    private final KindEmDAO kindEmDAO;
    private final CharEmDAO charEmDAO;

    public GetAllKindsHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
        this.charEmDAO = new CharEmDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> kindList = new ArrayList<>();
        try {
            List<KindEmEntity> listOfUsers = kindEmDAO.findAll();
            Gson gson = new Gson();
            for (KindEmEntity kindEm : listOfUsers) {
                Map<String, Object> kindsMap = new HashMap<>();

                kindsMap.put("kind_id", kindEm.getKindId());
                kindsMap.put("kind_name", kindEm.getKindName());
                CharEmEntity charEm = charEmDAO.findById(kindEm.getIdChar());
                kindsMap.put("char_name", charEm.getCharName());

                kindList.add(kindsMap);
            }

            return gson.toJson(kindList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
