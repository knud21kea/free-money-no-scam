package com.example.freemoneynoscam.services;

public class ValidateEmailService
{
    public boolean isEmailValid(String email)
    {
        // Only valid if address has exactly 1 '@'
        int trunk = email.length() - email.replace("@", "").length();
        // And at least 1 '.'
        return trunk == 1 && email.contains(".");
    }
}
