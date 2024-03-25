package com.example.easerver.Handlers.AdminHandlers;

import com.example.easerver.Handlers.BaseHandlers.PostHandler;

import com.example.easerver.Models.SystemUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddNewUserHandler extends PostHandler {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected int handlePostInput(String output) {
        try {

//            {
//                "login": "mumiayr@gmail.com",
//                    "password": "123",
//                    "status": true
//            }

            SystemUser systemUser = gson.fromJson(output, SystemUser.class);
            System.out.println(systemUser.getLogin() + " " + systemUser.getPassword() + " " + systemUser.getStatus());
            return 200;
        } catch (Exception e) {
            return 500;
        }
    }
}
