package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.ApplicantModels.ApplicantProfile;
import com.example.easerver.Models.ApplicantModels.ApplicantReg;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApplicantSignUpHandler extends PostHandler {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostInput(String output) {
        try {
            ApplicantReg applicantReg = gson.fromJson(output, ApplicantReg.class);
            ApplicantProfile applicantProfile = applicantReg.getProfile();
            System.out.println(applicantProfile.getName() + " " +
                    applicantProfile.getSurname() + " " +
                    applicantProfile.getPatronymic() + " " +
                    applicantProfile.getEmail() + " " +
                    applicantProfile.getHomeAddress() + " " +
                    applicantProfile.getWorkAddress() + " " +
                    applicantReg.getPassword()
            );
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
