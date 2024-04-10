package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.Entities.UserDataEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.ApplicantModels.ApplicantAuth;
import com.example.easerver.Services.UserAuthenticator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApplicantSignInHandler extends PostHandler {

    private final ApplicantDAO applicantDAO;

    public ApplicantSignInHandler() {
        this.applicantDAO = new ApplicantDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostRequest(String output) {
        try {
            ApplicantAuth applicantAuth = gson.fromJson(output, ApplicantAuth.class);
            System.out.println(applicantAuth.getEmail());

            UserDataEntity userDataEntity = applicantDAO.findByEmail(applicantAuth.getEmail());
            if (userDataEntity != null) {
                return UserAuthenticator.authenticateUser(userDataEntity.getPassword(), applicantAuth.getPassword());
            } else {
                System.out.println("Пользователь не найден");
                return 404;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
