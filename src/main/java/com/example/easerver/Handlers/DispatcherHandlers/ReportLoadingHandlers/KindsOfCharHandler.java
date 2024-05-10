package com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers;

import com.example.easerver.DBTransactions.DAO.CharEmDAO;
import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.DBTransactions.IMPL.CharEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.KindEmDAOImpl;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KindsOfCharHandler extends GetHandler {

    private final KindEmDAO kindEmDAO;
    private final CharEmDAO charEmDAO;

    Gson gson = new Gson();

    public KindsOfCharHandler() {
        this.kindEmDAO = new KindEmDAOImpl();
        this.charEmDAO = new CharEmDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<String> kindNames = new ArrayList<>();
        try {
            int char_id = charEmDAO.getCharIdByName(params.get("char"));
            List<KindEmEntity> kinds = kindEmDAO.findByCharId(char_id);
            for (KindEmEntity kind : kinds) {
                kindNames.add(kind.getKindName());
            }
            return gson.toJson(kindNames);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки сервисов реагирования ЧС", e);
        }
    }
}
