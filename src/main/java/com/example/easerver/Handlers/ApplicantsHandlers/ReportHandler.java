package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.DAO.ReportDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ReportDAOImpl;
import com.example.easerver.Entities.ReportsEntity;
import com.example.easerver.Entities.UserDataEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.Report.Report;
import com.example.easerver.Models.Report.ReportComponents;
import com.example.easerver.Models.Report.ReportTableData;
import com.example.easerver.ServerManagers.WebSocketServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Timestamp;
import java.time.LocalDateTime;


import static com.example.easerver.Services.DateTimeFormat.formatTimestamp;

public class ReportHandler extends PostHandler {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final ReportDAO reportDAO;
    private final ApplicantDAO applicantDAO;

    public ReportHandler() {
        this.reportDAO = new ReportDAOImpl();
        this.applicantDAO = new ApplicantDAOImpl();
    }

    @Override
    public int handlePostRequest(String output) {
        try {
            Report report = gson.fromJson(output, Report.class);
            ReportsEntity reportEntity = getReportsEntity(report);

            reportDAO.save(reportEntity);
            System.out.println("Добавлено новое заявление");
            WebSocketServer.sendMessageToAll(gson.toJson(setNewReportMap(reportEntity)));
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }

    private ReportTableData setNewReportMap(ReportsEntity reportsEntity) {
        UserDataEntity userData = applicantDAO.findByEmail(reportsEntity.getUserEmail());
        return new ReportTableData(
                reportsEntity.getIdReport(),
                reportsEntity.getType(),
                userData.getName(),
                formatTimestamp(reportsEntity.getTimestamp()),
                reportsEntity.getPlace(),
                reportsEntity.getWasSeen()
                );
    }

    private ReportsEntity getReportsEntity(Report report) {
        ReportComponents reportComponents = report.getReportComponents();
        ReportsEntity reportsEntity = new ReportsEntity();
        Timestamp currentDate = Timestamp.valueOf(LocalDateTime.now());

        reportsEntity.setType(reportComponents.type);
        reportsEntity.setPlace(reportComponents.place);
        reportsEntity.setTimestamp(new Timestamp(reportComponents.timestamp));
        reportsEntity.setAdditionalInfo(reportComponents.additionalInfo);
        reportsEntity.setCasualtiesAmount(reportComponents.casualtiesAmount);
        reportsEntity.setIsUserInDanger(reportComponents.isUserInDanger);
        reportsEntity.setAreThereAnyCasualties(reportComponents.areThereAnyCasualties);
        reportsEntity.setUserEmail(report.email);
        reportsEntity.setWasSeen(false);
        reportsEntity.setRecievedDateTime(currentDate);

        return reportsEntity;
    }
}
