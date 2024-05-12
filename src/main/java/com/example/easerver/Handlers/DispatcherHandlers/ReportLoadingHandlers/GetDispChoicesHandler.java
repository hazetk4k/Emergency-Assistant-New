package com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.DBTransactions.IMPL.SystemUserDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;

public class GetDispChoicesHandler extends GetHandler {

    private final DispChoiceDAO dispChoiceDAO;
    private final SystemUserDAO systemUserDAO;

    Gson gson = new Gson();

    JsonObject json = new JsonObject();

    public GetDispChoicesHandler() {
        this.dispChoiceDAO = new DispChoiceDAOImpl();
        this.systemUserDAO = new SystemUserDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        try {
            DispChoiceEntity dispChoice = dispChoiceDAO.findByReportId(Integer.parseInt(params.get("report_id")));
            if (dispChoice == null) {
                return null;
            } else {
                json.addProperty("choice_id", dispChoice.getId());
                json.addProperty("name_char", dispChoice.getNameChar());
                json.addProperty("name_kind", dispChoice.getNameKind());
                json.addProperty("services", dispChoice.getServices());
                json.addProperty("dead_amount", dispChoice.getDiedAmount());
                json.addProperty("people_amount", dispChoice.getPeopleAmount());
                json.addProperty("dispatcher_id", dispChoice.getDispatcherId());
                json.addProperty("additional_services", dispChoice.getAdditionalServices());
                json.addProperty("stage", dispChoice.getStage());
                json.addProperty("district_name", dispChoice.getDistrictName());
                json.addProperty("dispatcher_login", systemUserDAO.findById(dispChoice.getDispatcherId()).getLoginSyst());

                return gson.toJson(json);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
