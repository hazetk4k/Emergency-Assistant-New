package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.Entities.UserDataEntity;
import com.example.easerver.Handlers.BaseHandlers.PostHandler;
import com.example.easerver.Models.ApplicantModels.ApplicantAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class ApplicantSignInHandler extends PostHandler {

    private final ApplicantDAO applicantDAO;

    public ApplicantSignInHandler() {
        this.applicantDAO = new ApplicantDAOImpl();
    }

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostInput(String output) {
        try {
            ApplicantAuth applicantAuth = gson.fromJson(output, ApplicantAuth.class);
            System.out.println(applicantAuth.getEmail());

            UserDataEntity userDataEntity = applicantDAO.findByEmail(applicantAuth.getEmail());
            if (userDataEntity != null) {
                if (Objects.equals(userDataEntity.getPassword(), applicantAuth.getPassword())) {
                    System.out.println("Все прошло");
                    return 200;
                } else {
                    System.out.println("Не верный пароль");
                    return 401;
                }
            } else {
                System.out.println("Пользователь не найден");
                return 404; //409
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
