package com.example.easerver.Handlers.DispatcherHandlers.StatisticsHandlers;

import com.example.easerver.DBTransactions.DAO.DispChoiceDAO;
import com.example.easerver.DBTransactions.IMPL.DispChoiceDAOImpl;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Entities.ReportsEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.example.easerver.Services.DateTimeFormat;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetTodayReports extends GetHandler {

    private final DispChoiceDAO dispChoiceDAO;
    Gson gson = new Gson();

    public GetTodayReports() {
        this.dispChoiceDAO = new DispChoiceDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        JsonArray json_array = new JsonArray();
        List<DispChoiceEntity> dispChoiceList = dispChoiceDAO.findAllChoicesByStage("5");
        LocalDate currentDate = LocalDate.now();
        for (DispChoiceEntity dispChoice : dispChoiceList) {
            ReportsEntity report = dispChoice.getReportsByRepotId();
            Timestamp receive_time = report.getRecievedDateTime();
            Timestamp end_actions_time = dispChoice.getEndActionsTime();
            if (isFinishedToday(receive_time, end_actions_time, currentDate)) {
                JsonObject json = new JsonObject();
                json.addProperty("reportId", report.getIdReport());
                json.addProperty("receiveTime", DateTimeFormat.formatTimestamp(report.getRecievedDateTime()));
                json.addProperty("endActionsTime", DateTimeFormat.formatTimestamp(dispChoice.getEndActionsTime()));
                json.addProperty("addressDistrict", report.getPlace() + ", " + dispChoice.getDistrictName());
                json.addProperty("typeName", report.getType());

                List<String> services = servicesListForm(dispChoice.getServices() + dispChoice.getAdditionalServices());
                JsonArray servicesArray = new JsonArray();
                for (String service : services) {
                    servicesArray.add(service);
                }
                json.add("services", servicesArray);
                json_array.add(json);
            }
        }
        return gson.toJson(json_array);
    }

    private boolean isFinishedToday(Timestamp startTimestamp, Timestamp endTimestamp, LocalDate currentDate) {
        LocalDateTime startDateTime = startTimestamp.toLocalDateTime();
        LocalDateTime endDateTime = endTimestamp.toLocalDateTime();

        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = endDateTime.toLocalDate();

        if (startDate.isEqual(currentDate) || endDate.isEqual(currentDate)) {
            System.out.println("Заявление выполнялось в пределах сегодняшнего дня.");
            return true;
        } else {
            System.out.println("Заявление не выполнялось в пределах сегодняшнего дня.");
            return false;
        }
    }

    private List<String> servicesListForm(String services) {
        String[] servicesArray = services.split("\n");

        List<String> servicesList = new ArrayList<>();

        for (String service : servicesArray) {
            String trimmedService = service.trim().replaceAll("//", ", Транспорт: ");
            if (!trimmedService.isEmpty()) {
                servicesList.add(trimmedService);
            }
        }
        return servicesList;
    }
}
