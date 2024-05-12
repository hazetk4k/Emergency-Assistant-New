package com.example.easerver.Handlers.AdminHandlers.AutoSettings;

import com.example.easerver.DBTransactions.DAO.AutoDAO;
import com.example.easerver.DBTransactions.IMPL.AutoDAOImpl;
import com.example.easerver.Entities.AutoEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllAutosHandler extends GetHandler {
    private final AutoDAO autoDAO;

    public GetAllAutosHandler() {
        this.autoDAO = new AutoDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<Object> autoList = new ArrayList<>();
        try {
            List<AutoEntity> autos = autoDAO.findAll();
            Gson gson = new Gson();
            for (AutoEntity auto : autos) {
                Map<String, Object> mapAuto = getStringObjectMap(auto);
                autoList.add(mapAuto);
            }
            return gson.toJson(autoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> getStringObjectMap(AutoEntity auto) {
        Map<String, Object> mapAuto = new HashMap<>();
        int auto_id = auto.getAutoId();
        String mark = auto.getMark();
        String number = auto.getAutoNum();
        String district = auto.getDistrictsByDistrictId().getDistrictName();
        String service = auto.getServiceByServiceId().getServiceName();

        mapAuto.put("auto_id", auto_id);
        mapAuto.put("mark", mark);
        mapAuto.put("number", number);
        mapAuto.put("district", district);
        mapAuto.put("service", service);
        return mapAuto;
    }
}
