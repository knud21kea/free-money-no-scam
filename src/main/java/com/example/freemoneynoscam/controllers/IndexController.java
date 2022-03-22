/*
Free Money Startup
Exercise with PRG (Post Redirect Get)
With a full stack (HTML5/CSS3, Java Spring/Thymeleaf, MySQL) application, local network
Original CSS and base code @Nicklas Dean

There are 4 endpoints:
/index - where the user can enter an email address in a text input form (POST)
/confirmation - redirect to a page confirming a successful database addition (GET)
/rejection - redirect to an explanation of failure, either invalid or already existing (GET)
/list - showing all records in the database (GET)

@Graham John Heaven - KEA exercise March 2022
*/

package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.models.Email;
import com.example.freemoneynoscam.services.ValidateEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class IndexController
{
    private final ValidateEmailService ves = new ValidateEmailService();
    private String email, invalid;

    @GetMapping("/index")
    public String start()
    {
        return "index";
    }

    @PostMapping("/index")
    public String addEmail(WebRequest emailFromForm)
    {
        email = emailFromForm.getParameter("email");
        boolean validEmail = ves.isEmailValid(email);
        boolean emailExits = ves.isEmailExisting(email);
        invalid = ves.addValidEmail(email, validEmail, emailExits);
        return (validEmail && !emailExits) ? "redirect:/confirmation" : "redirect:/rejection";
    }

    @GetMapping("/confirmation")
    public String emailAdded(Model model)
    {
        model.addAttribute("email", email);
        return "confirmation";
    }

    @GetMapping("/rejection")
    public String emailInvalid(Model model)
    {
        model.addAttribute("email", email);
        model.addAttribute("reason", invalid);
        return "rejection";
    }

    // Adds a list of added emails from the db, like a dev mode feature
    @GetMapping("/list")
    public String listEmails(Model model)
    {
        ArrayList<Email> emails = ves.getAddedEmails(); // Service layer interrogates the repository
        model.addAttribute("emailList", emails); // Thymeleaf adds list of email objects
        return "list";
    }
}
