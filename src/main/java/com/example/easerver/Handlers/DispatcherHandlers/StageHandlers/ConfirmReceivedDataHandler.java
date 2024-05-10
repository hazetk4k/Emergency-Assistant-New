package com.example.easerver.Handlers.DispatcherHandlers.StageHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.Report.Stages.Stage3Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfirmReceivedDataHandler extends PostHandler {

    private final DispChoiceDAO dispChoiceDAO;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfirmReceivedDataHandler() {
        this.dispChoiceDAO = new DispChoiceDAOImpl();
    }

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            Stage3Model model = gson.fromJson(requestBody, Stage3Model.class);
            DispChoiceEntity dispChoice = dispChoiceDAO.findByReportId(model.getReport_id());

            String time = model.getReceivedDataDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            Timestamp timestamp = Timestamp.valueOf(dateTime);

            dispChoiceDAO.confirmReceivedData(dispChoice, timestamp, model.getPeople_in_area_amount(), model.getDied_in_disaster_amount());
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
