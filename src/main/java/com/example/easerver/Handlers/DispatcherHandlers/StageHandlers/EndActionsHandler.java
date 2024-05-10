package com.example.easerver.Handlers.DispatcherHandlers.StageHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.Report.Stages.Stage5Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EndActionsHandler extends PostHandler {

    private final DispChoiceDAO dispChoiceDAO;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public EndActionsHandler() {
        this.dispChoiceDAO = new DispChoiceDAOImpl();
    }

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            Stage5Model model = gson.fromJson(requestBody, Stage5Model.class);
            DispChoiceEntity dispChoice = dispChoiceDAO.findByReportId(model.getReport_id());

            String time = model.getEnd_actions_time();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            Timestamp timestamp = Timestamp.valueOf(dateTime);

            dispChoiceDAO.confirmEndActionsTime(dispChoice, timestamp);
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
