package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.DBTransactions.DAO.ApplicantDAO;
import com.example.easerver.DBTransactions.IMPL.ApplicantDAOImpl;
import com.example.easerver.Models.ApplicantModels.ApplicantProfile;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.example.easerver.Entities.UserDataEntity;

import java.io.IOException;
import java.io.OutputStream;

public class ApplicantProfileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            ApplicantDAO applicantDAO = new ApplicantDAOImpl();

            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("GET")) {
                String query = exchange.getRequestURI().getQuery();
                String email = null;

                if (query != null) {
                    String[] queryParams = query.split("=");
                    if (queryParams.length == 2 && "email".equals(queryParams[0])) {
                        email = queryParams[1];
                    }
                }
                Gson gson = new Gson();
                if (email != null) {
                    UserDataEntity userProfile = applicantDAO.findByEmail(email);
                    ApplicantProfile applicant = new ApplicantProfile(userProfile.getName(),
                            userProfile.getSurname(),
                            userProfile.getPatronymic(),
                            userProfile.getHomeAddress(),
                            userProfile.getWorkAddress(),
                            userProfile.getEmail());
                    String string = gson.toJson(applicant);
                    byte[] bytes = string.getBytes();

                    exchange.sendResponseHeaders(200, bytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(bytes);
                    }
                } else {
                    exchange.sendResponseHeaders(500, -1);
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            exchange.sendResponseHeaders(500, -1);
        }
    }
}

