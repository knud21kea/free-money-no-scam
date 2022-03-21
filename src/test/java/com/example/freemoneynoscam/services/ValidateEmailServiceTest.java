package com.example.freemoneynoscam.services;

import com.example.freemoneynoscam.models.Email;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ValidateEmailServiceTest {
    //Arrange
    ValidateEmailService service = new ValidateEmailService();

    @Test
    void isEmailValid() {
        String em1 = "nifr@kea.dk";
        String em2 = "nifr@dk";
        String em3 = ".@";
        String em4 = "a@b.c";
        String em5 = "a@b.c.d";
        String em6 = "a@b@c.d";

        //Act
        boolean result1 = service.isEmailValid(em1);
        boolean result2 = service.isEmailValid(em2);
        boolean result3 = service.isEmailValid(em3);
        boolean result4 = service.isEmailValid(em4);
        boolean result5 = service.isEmailValid(em5);
        boolean result6 = service.isEmailValid(em6);

        //Assert
        assertTrue(result1);
        assertFalse(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
        assertFalse(result6);
    }

    @Test
    void getAddedEmails() {
        //Arrange
        String e0 = "gh@mail.co.uk";
        String e1 = "a@b.c";
        String e2 = "grah0070@stud.kea.dk";
        String e3 = "xxx@z.com";
        String e4 = "me@isp.dk";
        String e5 = "gh@mail.dk";
        String e6 = "bb@cc.dd";
        String e7 = "xyx@mail.se";
        String e8 = "zzz@z.net";
        String e9 = "a@info.net";

        //Act
        ArrayList<Email> emails = service.getAddedEmails();

        //Assert
        assertEquals(e0,emails.get(0).getEmailAddress());
        assertEquals(e1,emails.get(1).getEmailAddress());
        assertEquals(e2,emails.get(2).getEmailAddress());
        assertEquals(e3,emails.get(3).getEmailAddress());
        assertEquals(e4,emails.get(4).getEmailAddress());
        assertEquals(e5,emails.get(5).getEmailAddress());
        assertEquals(e6,emails.get(6).getEmailAddress());
        assertEquals(e7,emails.get(7).getEmailAddress());
        assertEquals(e8,emails.get(8).getEmailAddress());
        assertEquals(e9,emails.get(9).getEmailAddress());
    }
}
