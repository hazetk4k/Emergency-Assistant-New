package com.example.easerver.Handlers.AdminHandlers.AutoSettings;

import com.example.easerver.DBTransactions.DAO.AutoDAO;
import com.example.easerver.DBTransactions.DAO.DistrictsDAO;
import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.AutoDAOImpl;
import com.example.easerver.DBTransactions.IMPL.DistrictsDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Entities.AutoEntity;
import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Entities.ServiceEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.AdminSettingsModels.Auto;
import com.example.easerver.Models.Report.Stages.Stage2Model;
import com.example.easerver.ServerManagers.WebSocketServer;
import com.example.easerver.Services.ModelManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddNewAutoHandler extends PostHandler {
    Gson gson = new Gson();
    private final AutoDAO autoDAO;
    private final ServicesDAO servicesDAO;

    private final DistrictsDAO districtsDAO;

    public AddNewAutoHandler() {
        this.autoDAO = new AutoDAOImpl();
        this.districtsDAO = new DistrictsDAOImpl();
        this.servicesDAO = new ServicesDAOImpl();
    }

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            Auto model = gson.fromJson(requestBody, Auto.class);
            AutoEntity autoEntity = new AutoEntity();

            if (autoDAO.findByNumber(model.getNumber()) != null) {
                return 409;
            } else {
                autoEntity.setMark(model.getMark());
                autoEntity.setAutoNum(model.getNumber());
                int service_id = servicesDAO.findServiceIdByName(model.service_name);
                int district_id = districtsDAO.findByName(model.getDistrict_name());
                autoEntity.setServiceByServiceId(servicesDAO.findById(service_id));
                autoEntity.setDistrictsByDistrictId(districtsDAO.findById(district_id));

                autoDAO.save(autoEntity);
                return 200;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
