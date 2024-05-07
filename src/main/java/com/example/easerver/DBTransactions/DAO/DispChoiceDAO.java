package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.DispChoiceEntity;

public interface DispChoiceDAO extends GenericDAO<DispChoiceEntity, Integer> {

    DispChoiceEntity findByReportId(int report_id);
}
