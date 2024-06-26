package com.example.easerver.Handlers.DispatcherHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.DAO.ReportDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ReportDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Entities.ReportsEntity;
import com.example.easerver.Entities.UserDataEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.example.easerver.Services.ModelManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import static com.example.easerver.Services.DateTimeFormat.formatTimestamp;

public class AllReportsHandler extends GetHandler {
    private final ApplicantDAO applicantDAO;
    private final ReportDAO reportDAO;

    private final DispChoiceDAO dispChoiceDAO;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public AllReportsHandler() {
        this.applicantDAO = new ApplicantDAOImpl();
        this.reportDAO = new ReportDAOImpl();
        this.dispChoiceDAO = new DispChoiceDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> reportsList = new ArrayList<>();
        try {
            List<UserDataEntity> users = applicantDAO.findAll();
            for (UserDataEntity user : users) {
                String fullName = "";
                if (user.getPatronymic() == null || Objects.equals(user.getPatronymic(), "")) {
                    fullName = user.getSurname() + " " + user.getName().charAt(0) + ". ";
                } else {
                    fullName = user.getSurname() + " " + user.getName().charAt(0) + ". " + user.getPatronymic().charAt(0) + ". ";
                }

                List<ReportsEntity> userReportsList = reportDAO.getReportsByUserEmail(user.getEmail());

                for (ReportsEntity report : userReportsList) {
                    Map<String, Object> reportMap = new HashMap<>();

                    reportMap.put("id", report.getIdReport());
                    reportMap.put("type", report.getType());
                    reportMap.put("fio", fullName);
                    String formatDateTime = formatTimestamp(report.getTimestamp());
                    reportMap.put("timestamp", formatDateTime);
                    reportMap.put("place", report.getPlace());
                    String stageName;

                    if (report.getWasSeen()) {
                        stageName = getStageName(report);
                    } else {
                        stageName = "Новое заявление";
                    }
                    reportMap.put("stage_name", stageName);
                    reportsList.add(reportMap);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gson.toJson(reportsList);

    }

    protected String getStageName(ReportsEntity report) {
        DispChoiceEntity dispChoice = dispChoiceDAO.findByReportId(report.getIdReport());
        String stage = dispChoice.getStage();
        ModelManager modelManager = new ModelManager();

        return modelManager.getStageName(stage);
    }
}
