package com.example.easerver.Services;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserAuthenticator {
    public static int authenticateUser(String entityPassword, String userPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(userPassword.toCharArray(), entityPassword);
        if (result.verified) {
            return 200;
        } else {
            System.out.println("Не верный пароль");
            return 401;
        }
    }
}
