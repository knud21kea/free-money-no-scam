package com.example.freemoneynoscam.services;

import com.example.freemoneynoscam.repositories.DbHandler;

public class UpdateEmailService
{
    private final DbHandler db = new DbHandler();

    public void addEmailToDb(String email) {
        db.updateDb(email);
    }
}
