package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.ApplicantModels.ApplicantAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApplicantSignInHandler extends PostHandler {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    protected int handlePostInput(String output) {
        try {

//            {
//                "email": "mumiayr@gmail.com",
//                "password": "123",
//            }

            ApplicantAuth applicantAuth = gson.fromJson(output, ApplicantAuth.class);
            System.out.println(applicantAuth.getEmail() + " " + applicantAuth.getPassword());
            return 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
