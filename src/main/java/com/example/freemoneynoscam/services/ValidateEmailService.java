package com.example.freemoneynoscam.services;

import com.example.freemoneynoscam.repositories.DbHandler;

public class ValidateEmailService
{
    private final DbHandler db = new DbHandler();

    public boolean isEmailValid(String email)
    {
        // Only valid if address has exactly 1 '@'
        int trunk = email.length() - email.replace("@", "").length();
        // And at least 1 '.'
        boolean validEmail = trunk == 1 && email.contains(".");
        if (validEmail) {
           db.updateDb(email);
        }
        return validEmail;
    }
}
