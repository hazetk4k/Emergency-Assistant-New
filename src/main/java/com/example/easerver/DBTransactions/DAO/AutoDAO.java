package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.AutoEntity;
import java.util.List;

public interface AutoDAO extends GenericDAO<AutoEntity, Integer>{

    List<AutoEntity> getAutoByServiceAndDistrict(int service_id, int district_id);
}
