package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.ReportDAO;
import com.example.easerver.Entities.ReportsEntity;

public class ReportDAOImpl extends GenericDAOImpl<ReportsEntity, Integer> implements ReportDAO{

    public ReportDAOImpl() {
        super(ReportsEntity.class);
    }
}
