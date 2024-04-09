package com.example.easerver.Handlers.DispatcherHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.DAO.ReportDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ReportDAOImpl;
import com.example.easerver.Entities.ReportsEntity;
import com.example.easerver.Entities.UserDataEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.NoResultException;

import java.util.*;

public class AllReportsHandler extends GetHandler {
    private final ApplicantDAO applicantDAO;
    private final ReportDAO reportDAO;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public AllReportsHandler() {
        this.applicantDAO = new ApplicantDAOImpl();
        this.reportDAO = new ReportDAOImpl();
    }

    @Override
    protected String handleGet(Map<String, String> params) {
        List<Object> reportsList = new ArrayList<>();
        try {
            List<UserDataEntity> users = applicantDAO.findAll();
            for (UserDataEntity user : users) {
                String fullName = "";
                if (user.getPatronymic() == null || Objects.equals(user.getPatronymic(), "")) {
                    fullName = user.getSurname() + " " + user.getName().charAt(0) + ". ";
                } else {
                    fullName = user.getSurname() + " " + user.getName().charAt(0) + ". " + user.getName().charAt(0);
                }

                List<ReportsEntity> userReportsList = reportDAO.getReportsByUserEmail(user.getEmail());

                for (ReportsEntity report : userReportsList) {
                    Map<String, Object> reportMap = new HashMap<>();

                    reportMap.put("id", report.getIdReport());
                    reportMap.put("type", report.getType());
                    reportMap.put("fio", fullName);
                    reportMap.put("timestamp", report.getTimestamp());
                    reportMap.put("place", report.getPlace());
                    reportMap.put("wasSeen", report.getWasSeen());

                    reportsList.add(reportMap);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gson.toJson(reportsList);

    }
}