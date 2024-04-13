package com.example.easerver.Handlers.AdminHandlers.RelationsSettings;

import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Entities.ServiceEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetUpServicesHandler extends GetHandler {

    private final ServicesDAO servicesDAO;

    public SetUpServicesHandler(){
        this.servicesDAO = new ServicesDAOImpl();
    }
    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> namesList = new ArrayList<>();
        try {
            List<ServiceEntity> listOfServices = servicesDAO.findAll();
            Gson gson = new Gson();
            for (ServiceEntity serviceEntity : listOfServices) {
                namesList.add(serviceEntity.getServiceName());
            }
            return gson.toJson(namesList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
