package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.DBTransactions.DAO.ReportDAO;
import com.example.easerver.DBTransactions.IMPL.ReportDAOImpl;
import com.example.easerver.Entities.ReportsEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.Report.Report;
import com.example.easerver.Models.Report.ReportComponents;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ReportHandler extends PostHandler {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final ReportDAO reportDAO;

    public ReportHandler() {
        this.reportDAO = new ReportDAOImpl();
    }

    @Override
    public int handlePostRequest(String output) {
        try {
            Report report = gson.fromJson(output, Report.class);
            ReportsEntity reportsEntity = getReportsEntity(report);

            reportDAO.save(reportsEntity);
            System.out.println("Добавлено новое заявление");

            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }

    private static ReportsEntity getReportsEntity(Report report) {
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
