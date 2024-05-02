package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.KindEmEntity;
import java.util.List;

public interface KindEmDAO extends GenericDAO<KindEmEntity, Integer>{

    int getKindIdByName(String name);

    List<KindEmEntity> findByCharId(int char_id);
}
