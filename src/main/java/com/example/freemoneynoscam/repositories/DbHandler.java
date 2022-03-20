package com.example.freemoneynoscam.repositories;

import java.sql.*;

public class DbHandler
{
    private String sqlString;
    private Connection con;
    private Statement stmt;

    public DbHandler()
    {
    }

    public void connectDB()
    {
        try
        {
            String url = "jdbc:mysql://localhost:3306/emails_db";
            con = DriverManager.getConnection(url, "root", "ekch22Lmysql");
            stmt = con.createStatement();
            System.out.println("Connected.");
        } catch (Exception e)
        {
            System.out.println("Unable to connect");
        }
    }


    public void makeTableEmails()
    {
        try
        {
            // Check if table exists and create if not
            sqlString = "CREATE TABLE IF NOT EXISTS `emails_db`.`emails` (" +
                    "  `Email_id` INT NOT NULL AUTO_INCREMENT," +
                    "  `Email_address` VARCHAR(45) NOT NULL," +
                    "  PRIMARY KEY (`Email_id`));";
            stmt.executeUpdate(sqlString);
        } catch (Exception e)
        {
            System.out.println("DB error.");
        }
    }


    public void addEmail(String email)
    {
        try
        {
            sqlString = "INSERT INTO emails (`Email_address`) VALUES(?)";
            PreparedStatement statement = con.prepareStatement(sqlString);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (Exception e)
        {
            System.out.println("DB error.");
        }
    }
}
