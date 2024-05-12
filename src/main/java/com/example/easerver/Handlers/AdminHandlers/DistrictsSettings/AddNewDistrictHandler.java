package com.example.easerver.Handlers.AdminHandlers.DistrictsSettings;

import com.example.easerver.DBTransactions.DAO.DistrictsDAO;
import com.example.easerver.DBTransactions.IMPL.DistrictsDAOImpl;
import com.example.easerver.Entities.DistrictsEntity;
import com.example.easerver.Entities.KindEmEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.AdminSettingsModels.NewKindEm;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AddNewDistrictHandler extends PostHandler {

    private final DistrictsDAO districtsDAO;
    Gson gson = new Gson();

    public AddNewDistrictHandler() {
        this.districtsDAO = new DistrictsDAOImpl();
    }

    @Override
    protected int handlePostRequest(String requestBody) {
        try {
            JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
            DistrictsEntity district = new DistrictsEntity();

            String district_name = jsonObject.get("district_name").getAsString();

            if (districtsDAO.findByName(district_name) != -1) {
                return 409;
            }

            district.setDistrictName(district_name);
            districtsDAO.save(district);
            return 200;
        } catch (Exception e) {
            return 500;
        }
    }
}
