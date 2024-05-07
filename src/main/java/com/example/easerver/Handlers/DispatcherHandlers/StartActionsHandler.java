package com.example.easerver.Handlers.DispatcherHandlers;

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
import com.example.easerver.Models.Report.DispChoiceStartAction;
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
            DispChoiceStartAction dispatcherChoiceModel = gson.fromJson(requestBody, DispChoiceStartAction.class);
            DispChoiceEntity dispatcherChoiceEntity = new DispChoiceEntity();
            SystUserEntity systUserEntity = systemUserDAO.findByUserLogin(dispatcherChoiceModel.getDisp_login());
            ReportsEntity report = reportDAO.findById(dispatcherChoiceModel.getReport_id());

            dispatcherChoiceEntity.setNameKind(dispatcherChoiceModel.getKind_name());
            dispatcherChoiceEntity.setNameChar(dispatcherChoiceModel.getChar_name());

            String startActionTimeString = dispatcherChoiceModel.getStart_action_time();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime dateTime = LocalDateTime.parse(startActionTimeString, formatter);
            Timestamp startActionsTime = Timestamp.valueOf(dateTime);

            dispatcherChoiceEntity.setStartActionsTime(startActionsTime);
            dispatcherChoiceEntity.setReportsByRepotId(report);
            dispatcherChoiceEntity.setDispatcherId(systUserEntity.getIdSyst());

            dispChoiceDAO.save(dispatcherChoiceEntity);
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
