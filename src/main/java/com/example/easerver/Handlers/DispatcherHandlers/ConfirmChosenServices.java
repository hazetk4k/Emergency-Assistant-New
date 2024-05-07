package com.example.easerver.Handlers.DispatcherHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.Report.DispModel_stage2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmChosenServices extends PostHandler {

    private final DispChoiceDAO dispChoiceDAO;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfirmChosenServices() {
        this.dispChoiceDAO = new DispChoiceDAOImpl();
    }

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            DispModel_stage2 model = gson.fromJson(requestBody, DispModel_stage2.class);
            DispChoiceEntity dispChoiceEntity = dispChoiceDAO.findByReportId(model.getReport_id());

            String time = model.getConfirm_services_time();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            Timestamp timestamp = Timestamp.valueOf(dateTime);

            dispChoiceDAO.confirmChosenServices(dispChoiceEntity, model.getServices(), timestamp);

            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
