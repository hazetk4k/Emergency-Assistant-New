package com.example.easerver.Services;

import java.util.Objects;

public class UserAuthenticator {
    public static int authenticateUser(String entityPassword, String userPassword) {
        if (Objects.equals(entityPassword, userPassword)) {
            System.out.println("Все прошло");
            return 200;
        } else {
            System.out.println("Не верный пароль");
            return 401;
        }
    }
}
