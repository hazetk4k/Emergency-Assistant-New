package com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers;

import com.example.easerver.DBTransactions.DAO.CharEmDAO;
import com.example.easerver.DBTransactions.DAO.DistrictsDAO;
import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.CharEmDAOImpl;
import com.example.easerver.DBTransactions.IMPL.DistrictsDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Entities.CharEmEntity;
import com.example.easerver.Entities.DistrictsEntity;
import com.example.easerver.Entities.ServiceEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmergencyDataHandler extends GetHandler {

    private final ServicesDAO servicesDAO;

    private final CharEmDAO charEmDAO;

    private final DistrictsDAO districtsDAO;

    Gson gson = new Gson();
    Map<String, JsonArray> result = new HashMap<>();

    public EmergencyDataHandler() {
        this.charEmDAO = new CharEmDAOImpl();
        this.servicesDAO = new ServicesDAOImpl();
        this.districtsDAO = new DistrictsDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        try {
            List<CharEmEntity> listOfChars = charEmDAO.findAll();
            JsonArray charsArray = new JsonArray();
            for (CharEmEntity charEntity : listOfChars) {
                charsArray.add(charEntity.getCharName());
            }
            result.put("chars", charsArray);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки характеров ЧС", e);
        }

        try {
            List<ServiceEntity> listOfServices = servicesDAO.findAll();
            JsonArray servicesArray = new JsonArray();
            for (ServiceEntity serviceEntity : listOfServices) {
                servicesArray.add(serviceEntity.getServiceName());
            }
            result.put("services", servicesArray);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки сервисов реагирования ЧС", e);
        }

        try {
            List<DistrictsEntity> listOfDistricts = districtsDAO.findAll();
            JsonArray districtsArray = new JsonArray();
            for (DistrictsEntity districtsEntity : listOfDistricts) {
                districtsArray.add(districtsEntity.getDistrictName());
            }
            result.put("districts", districtsArray);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки сервисов реагирования ЧС", e);
        }

        return gson.toJson(result);
    }
}
