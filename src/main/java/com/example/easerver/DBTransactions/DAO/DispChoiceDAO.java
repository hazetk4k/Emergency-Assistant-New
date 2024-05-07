package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.DispChoiceEntity;
import com.example.easerver.Models.Report.DispModel_stage2;
import java.sql.Timestamp;

public interface DispChoiceDAO extends GenericDAO<DispChoiceEntity, Integer> {

    DispChoiceEntity findByReportId(int report_id);

    void confirmChosenServices(DispChoiceEntity dispChoice, String services, Timestamp timestamp);
}
