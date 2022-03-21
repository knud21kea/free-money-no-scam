package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.services.ValidateEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;

@Controller
public class IndexController
{
    private final ValidateEmailService ves = new ValidateEmailService();

    @GetMapping("/")
    public String start()
    {
        return "index";
    }

    @PostMapping("/")
    public String addEmail(@RequestParam("email") String email, RedirectAttributes attributes)
    {
        attributes.addAttribute("email", email);
        boolean validEmail = ves.isEmailValid(email);
        if (validEmail) {
            ves.addValidEmail(email); // Should this logic be in service layer?
        }
        return (validEmail) ? "redirect:/confirmation" : "redirect:/";
    }

    @GetMapping("/confirmation")
    public String emailAdded(@RequestParam String email, Model model)
    {
        model.addAttribute("email", email);
        return "confirmation";
    }

    // Adds a list of added emails from the db, like a dev mode feature
    @GetMapping("/list")
    public String listEmails(Model model)
    {
        ArrayList<String> emails = ves.getAddedEmails(); // Service layer interrogates the repository
        model.addAttribute("emailList",emails); // Could be a model class, but it's just a single string
        return "list";
    }
}
