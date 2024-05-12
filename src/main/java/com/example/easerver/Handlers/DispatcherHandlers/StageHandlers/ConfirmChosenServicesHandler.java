package com.example.easerver.Handlers.DispatcherHandlers.StageHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.Report.Stages.Stage2Model;
import com.example.easerver.ServerManagers.WebSocketServer;
import com.example.easerver.Services.ModelManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmChosenServicesHandler extends PostHandler {
    private final DispChoiceDAO dispChoiceDAO;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfirmChosenServicesHandler() {
        this.dispChoiceDAO = new DispChoiceDAOImpl();
    }

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            Stage2Model model = gson.fromJson(requestBody, Stage2Model.class);
            DispChoiceEntity dispChoiceEntity = dispChoiceDAO.findByReportId(model.getReport_id());

            String time = model.getConfirm_services_time();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            Timestamp timestamp = Timestamp.valueOf(dateTime);

            dispChoiceDAO.confirmChosenServices(dispChoiceEntity, model.getServices(), timestamp);

            ModelManager modelManager = new ModelManager();
            JsonObject object = modelManager.getStageName(dispChoiceEntity.getStage(), model.getReport_id());
            WebSocketServer.sendMessageToAll(gson.toJson(object));
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
