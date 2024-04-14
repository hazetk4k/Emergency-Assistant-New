package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.Entities.UserDataEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.ApplicantModels.ApplicantProfile;
import com.example.easerver.Models.ApplicantModels.ApplicantReg;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApplicantSignUpHandler extends PostHandler {
    private final ApplicantDAO applicantDAO;

    public ApplicantSignUpHandler() {
        this.applicantDAO = new ApplicantDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostRequest(String output) {
        try {

            ApplicantReg applicantReg = gson.fromJson(output, ApplicantReg.class);
            ApplicantProfile applicantProfile = applicantReg.getProfile();
            UserDataEntity user = new UserDataEntity(
                    applicantProfile.getName(),
                    applicantProfile.getSurname(),
                    applicantProfile.getPatronymic(),
                    applicantProfile.getHomeAddress(),
                    applicantProfile.getWorkAddress(),
                    applicantProfile.getEmail(),
                    applicantProfile.getPhoneNumber(),
                    applicantReg.getPassword()
            );

            applicantDAO.save(user);

            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
