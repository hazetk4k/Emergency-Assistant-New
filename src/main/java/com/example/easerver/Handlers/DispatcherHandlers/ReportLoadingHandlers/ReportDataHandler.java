package com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.DAO.ReportDAO;
import com.example.easerver.DBTransactions.DAO.TypeEmDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ReportDAOImpl;
import com.example.easerver.DBTransactions.IMPL.TypeEmDAOImpl;
import com.example.easerver.Entities.ReportsEntity;
import com.example.easerver.Entities.TypeEmEntity;
import com.example.easerver.Entities.UserDataEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.example.easerver.Services.DateTimeFormat;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ReportDataHandler extends GetHandler {

    private final ReportDAO reportDAO;

    private final ApplicantDAO applicantDAO;

    private final TypeEmDAO typeEmDAO;

    Gson gson = new Gson();

    public ReportDataHandler() {
        this.reportDAO = new ReportDAOImpl();
        this.applicantDAO = new ApplicantDAOImpl();
        this.typeEmDAO = new TypeEmDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        Map<String, Object> response = new HashMap<>();
        ReportsEntity reportsEntity;
        UserDataEntity userDataEntity;

        try {
            reportsEntity = reportDAO.findById(Integer.parseInt(params.get("report_id")));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки данных заявления", e);
        }

        try {
            userDataEntity = applicantDAO.findByEmail(reportsEntity.getUserEmail());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки данных заявителя", e);
        }

        response.put("report", getReportsData(reportsEntity));
        response.put("applicant", getApplicantData(userDataEntity));

        return gson.toJson(response);
    }

    private Map<String, Object> getApplicantData(UserDataEntity userDataEntity) {
        Map<String, Object> applicantMap = new HashMap<>();
        applicantMap.put("name", userDataEntity.getName());
        applicantMap.put("surname", userDataEntity.getSurname());
        applicantMap.put("patronymic", userDataEntity.getPatronymic());
        applicantMap.put("home_address", userDataEntity.getHomeAddress());
        applicantMap.put("work_address", userDataEntity.getWorkAddress());
        applicantMap.put("email", userDataEntity.getEmail());
        applicantMap.put("phone_number", userDataEntity.getPhoneNumber());
        return applicantMap;
    }

    private Map<String, Object> getReportsData(ReportsEntity reportsEntity) {
        Map<String, Object> reportMap = new HashMap<>();
        reportMap.put("type", reportsEntity.getType());
        reportMap.put("additional_info", reportsEntity.getAdditionalInfo());
        reportMap.put("place", reportsEntity.getPlace());
        reportMap.put("timestamp", DateTimeFormat.formatTimestamp(reportsEntity.getTimestamp()));
        reportMap.put("are_there_any_casualties", reportsEntity.getAreThereAnyCasualties());
        reportMap.put("casualties_amount", reportsEntity.getCasualtiesAmount());
        reportMap.put("user_in_danger", reportsEntity.getIsUserInDanger());
        reportMap.put("was_seen", reportsEntity.getWasSeen());
        reportMap.put("received_datetime", DateTimeFormat.formatTimestamp(reportsEntity.getRecievedDateTime()));

        TypeEmEntity type = typeEmDAO.findByTypeName(reportsEntity.getType());
        String recommendations;
        if(type == null){
            recommendations = "Cохраняйте спокойствие. Постарайтесь удалиться от потенциальной опасности на безопасное расстояние и ожидайте приезда служб.";

        } else {
            recommendations = type.getRecommendations();
        }

        reportMap.put("recommendations", recommendations);
        return reportMap;
    }
}
