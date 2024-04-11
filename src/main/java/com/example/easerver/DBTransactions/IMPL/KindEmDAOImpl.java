package com.example.easerver.DBTransactions.IMPL;

import com.example.easerver.DBTransactions.DAO.KindEmDAO;
import com.example.easerver.Entities.KindEmEntity;

public class KindEmDAOImpl extends GenericDAOImpl<KindEmEntity, Integer> implements KindEmDAO {
    public KindEmDAOImpl() {super(KindEmEntity.class);
    }
}
