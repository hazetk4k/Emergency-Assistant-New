package com.example.easerver.Handlers.DispatcherHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class GetDispChoicesHandler extends GetHandler {

    private final DispChoiceDAO dispChoiceDAO;

    Gson gson = new Gson();

    public GetDispChoicesHandler() {
        this.dispChoiceDAO = new DispChoiceDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        try {
            DispChoiceEntity dispChoice = dispChoiceDAO.findByReportId(Integer.parseInt(params.get("report_id")));
            if (dispChoice == null) {
                return null;
            } else {
                Map<String, Object> dispChoiceFields = new HashMap<>();

                dispChoiceFields.put("name_char", dispChoice.getNameChar());
                dispChoiceFields.put("name_kind", dispChoice.getNameKind());
                dispChoiceFields.put("services", dispChoice.getServices());

                return gson.toJson(dispChoiceFields);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
