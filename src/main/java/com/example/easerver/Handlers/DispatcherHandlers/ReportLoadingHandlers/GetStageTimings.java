package com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.example.easerver.Services.DateTimeFormat;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class GetStageTimings extends GetHandler {
    Gson gson = new Gson();
    private final DispChoiceDAO dispChoiceDAO;

    public GetStageTimings(){
        this.dispChoiceDAO = new DispChoiceDAOImpl();
    }
    @Override
    protected String handleGetRequest(Map<String, String> params) {
        DispChoiceEntity dispChoice = dispChoiceDAO.findById(Integer.parseInt(params.get("choice_id")));
        Map<String, String> timingMap = new HashMap<>();
        timingMap.put("start_time", DateTimeFormat.formatTimestamp(dispChoice.getStartActionsTime()));
        timingMap.put("call_services_time", DateTimeFormat.formatTimestamp(dispChoice.getCallServicesTime()));
        timingMap.put("received_data_time",DateTimeFormat.formatTimestamp(dispChoice.getReceiveDataTime()));
        timingMap.put("call_add_services_time",DateTimeFormat.formatTimestamp(dispChoice.getAdditionalServicesTime()));
        timingMap.put("end_time", DateTimeFormat.formatTimestamp(dispChoice.getEndActionsTime()));
        return gson.toJson(timingMap);
    }
}
