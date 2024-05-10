package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.DispChoiceEntity;
import java.sql.Timestamp;

public interface DispChoiceDAO extends GenericDAO<DispChoiceEntity, Integer> {

    DispChoiceEntity findByReportId(int report_id);

    void confirmChosenServices(DispChoiceEntity dispChoice, String services, Timestamp timestamp);

    void confirmReceivedData(DispChoiceEntity dispChoice, Timestamp timestamp, int people_amount, int died_amount);

    void confirmAdditionalServices(DispChoiceEntity dispChoice, Timestamp timestamp, String services);

    void confirmEndActionsTime(DispChoiceEntity dispChoice, Timestamp timestamp);
}
