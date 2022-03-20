package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.repositories.DbHandler;
import com.example.freemoneynoscam.services.UpdateEmailService;
import com.example.freemoneynoscam.services.ValidateEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController
{
    ValidateEmailService ves = new ValidateEmailService();
    UpdateEmailService ues = new UpdateEmailService();

    @GetMapping("/")
    public String start()
    {
        return "index";
    }

    @PostMapping("/")
    public String addEmail(@RequestParam("email") String email, RedirectAttributes attributes)
    {
        attributes.addAttribute("email", email);
        String redirect = "/";
        if (ves.isEmailValid(email))
        {
            redirect = "/confirmation";
        }
        return "redirect:" + redirect;
    }

    @GetMapping("/confirmation")
    public String emailAdded(@RequestParam String email, Model model)
    {
        model.addAttribute("email", email);
        ues.addEmailToDb(email); // add validated email address to database
        return "confirmation";
    }
}
