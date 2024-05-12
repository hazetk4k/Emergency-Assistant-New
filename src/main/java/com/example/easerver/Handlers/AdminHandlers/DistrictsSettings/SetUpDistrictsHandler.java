package com.example.easerver.Handlers.AdminHandlers.DistrictsSettings;

import com.example.easerver.DBTransactions.DAO.DistrictsDAO;
import com.example.easerver.DBTransactions.IMPL.DistrictsDAOImpl;
import com.example.easerver.Entities.CharEmEntity;
import com.example.easerver.Entities.DistrictsEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetUpDistrictsHandler extends GetHandler {
    private final DistrictsDAO districtsDAO;

    public SetUpDistrictsHandler(){
        this.districtsDAO = new DistrictsDAOImpl();
    }
    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> namesList = new ArrayList<>();
        try {
            List<DistrictsEntity> listOfDistricts = districtsDAO.findAll();
            Gson gson = new Gson();
            for (DistrictsEntity district : listOfDistricts) {
                namesList.add(district.getDistrictName());
            }
            return gson.toJson(namesList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
