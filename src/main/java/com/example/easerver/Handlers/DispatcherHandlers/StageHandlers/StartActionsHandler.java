package com.example.easerver.Handlers.DispatcherHandlers.StageHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.DAO.ReportDAO;
import com.example.easerver.DBTransactions.DAO.SystemUserDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ReportDAOImpl;
import com.example.easerver.DBTransactions.IMPL.SystemUserDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Entities.ReportsEntity;
import com.example.easerver.Entities.SystUserEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.Report.Stages.Stage1Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartActionsHandler extends PostHandler {
    private final DispChoiceDAO dispChoiceDAO;
    private final ReportDAO reportDAO;

    private final SystemUserDAO systemUserDAO;

    public StartActionsHandler() {
        this.dispChoiceDAO = new DispChoiceDAOImpl();
        this.reportDAO = new ReportDAOImpl();
        this.systemUserDAO = new SystemUserDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            Stage1Model model = gson.fromJson(requestBody, Stage1Model.class);
            DispChoiceEntity dispChoice = new DispChoiceEntity();
            SystUserEntity systUserEntity = systemUserDAO.findByUserLogin(model.getDisp_login());
            ReportsEntity report = reportDAO.findById(model.getReport_id());

            dispChoice.setNameKind(model.getKind_name());
            dispChoice.setNameChar(model.getChar_name());

            String startActionTimeString = model.getStart_action_time();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(startActionTimeString, formatter);
            Timestamp startActionsTime = Timestamp.valueOf(dateTime);

            dispChoice.setStartActionsTime(startActionsTime);
            dispChoice.setReportsByRepotId(report);
            dispChoice.setDispatcherId(systUserEntity.getIdSyst());
            dispChoice.setStage("1");

            dispChoiceDAO.save(dispChoice);
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
