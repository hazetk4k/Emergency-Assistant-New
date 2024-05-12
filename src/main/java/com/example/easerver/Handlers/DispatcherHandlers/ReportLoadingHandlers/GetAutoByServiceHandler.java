package com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers;

import com.example.easerver.DBTransactions.DAO.AutoDAO;
import com.example.easerver.DBTransactions.DAO.DistrictsDAO;
import com.example.easerver.DBTransactions.DAO.ServicesDAO;
import com.example.easerver.DBTransactions.IMPL.AutoDAOImpl;
import com.example.easerver.DBTransactions.IMPL.DistrictsDAOImpl;
import com.example.easerver.DBTransactions.IMPL.ServicesDAOImpl;
import com.example.easerver.Entities.AutoEntity;
import com.example.easerver.Entities.ServiceEntity;
import com.example.easerver.Entities.ServiceKindRelationEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAutoByServiceHandler extends GetHandler {

    private final AutoDAO autoDAO;
    private final DistrictsDAO districtsDAO;
    private final ServicesDAO servicesDAO;

    Gson gson = new Gson();

    public GetAutoByServiceHandler() {
        this.autoDAO = new AutoDAOImpl();
        this.districtsDAO = new DistrictsDAOImpl();
        this.servicesDAO = new ServicesDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        List<String> auto_list = new ArrayList<>();
        try {
            int service_id = servicesDAO.findServiceIdByName(params.get("service_name"));
            if (service_id == -1) return null;

            int district_id = districtsDAO.findByName(params.get("district_name"));
            if (district_id == -1) return null;

            List<AutoEntity> auto_entity_list = autoDAO.getAutoByServiceAndDistrict(service_id, district_id);
            for (AutoEntity auto : auto_entity_list) {
                auto_list.add(auto.getMark() + " " + auto.getAutoNum());
            }

            return gson.toJson(auto_list);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки транспорта служб", e);
        }
    }
}
