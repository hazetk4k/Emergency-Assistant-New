package com.example.easerver.Handlers.DispatcherHandlers;

import com.example.easerver.DBTransactions.DAO.CharEmDAO;
import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.DAO.TypeEmDAO;
import com.example.easerver.DBTransactions.IMPL.CharEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.TypeEmDAOImpl;
import com.example.easerver.Entities.CharEmEntity;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Entities.TypeEmEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class GetKindCharByTypeHandler extends GetHandler {

    private final TypeEmDAO typeEmDAO;
    private final KindEmDAO kindEmDAO;
    private final CharEmDAO charEmDAO;

    Gson gson = new Gson();

    public GetKindCharByTypeHandler() {
        this.typeEmDAO = new TypeEmDAOImpl();
        this.kindEmDAO = new KindEmDAOImpl();
        this.charEmDAO = new CharEmDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        TypeEmEntity typeEmEntity = typeEmDAO.findByTypeName(params.get("type_name"));
        if (typeEmEntity == null) {
            return null;
        }
        KindEmEntity kindEmEntity = kindEmDAO.findById(typeEmEntity.getIdKind());
        CharEmEntity charEmEntity = charEmDAO.findById(kindEmEntity.getIdChar());

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("kind_name", kindEmEntity.getKindName());
        responseMap.put("char_name", charEmEntity.getCharName());
        return gson.toJson(responseMap);
    }
}
