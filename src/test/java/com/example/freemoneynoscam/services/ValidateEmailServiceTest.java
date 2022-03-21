package com.example.freemoneynoscam.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateEmailServiceTest {
    //Arrange
    ValidateEmailService service = new ValidateEmailService();

    @Test
    void isEmailValid() {
        //Arrange
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

}
