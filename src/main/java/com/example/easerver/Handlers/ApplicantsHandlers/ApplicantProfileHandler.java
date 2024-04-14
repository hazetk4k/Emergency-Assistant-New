package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.Entities.UserDataEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.example.easerver.Models.ApplicantModels.ApplicantProfile;
import com.google.gson.Gson;

import java.util.Map;

public class ApplicantProfileHandler extends GetHandler {

    private final ApplicantDAO applicantDAO;

    public ApplicantProfileHandler() {
        this.applicantDAO = new ApplicantDAOImpl();
    }

    @Override
    protected String handleGetRequest(Map<String, String> params) {
        try {
            UserDataEntity userProfile = applicantDAO.findByEmail(params.get("email"));
            Gson gson = new Gson();

            ApplicantProfile applicant = new ApplicantProfile(
                    userProfile.getName(),
                    userProfile.getSurname(),
                    userProfile.getPatronymic(),
                    userProfile.getHomeAddress(),
                    userProfile.getWorkAddress(),
                    userProfile.getEmail(),
                    userProfile.getPhoneNumber()
            );


            return gson.toJson(applicant);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

