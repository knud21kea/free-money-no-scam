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
        return trunk == 1 && email.contains(".");
    }

    public void addValidEmail(String email)
    {
        db.updateDb(email); // Could check if already added
    }
}
