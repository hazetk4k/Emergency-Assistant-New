package com.example.easerver.DBTransactions.DAO;

import com.example.easerver.Entities.ReportsEntity;

import java.util.List;

public interface ReportDAO extends GenericDAO<ReportsEntity, Integer>{
    List<ReportsEntity> getReportsByUserEmail(String email);

    void updateWasSeenById(int reportId);
}
