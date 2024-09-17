package fr.challenge.filerouge_utopios.controller;

import fr.challenge.filerouge_utopios.entity.User;
import fr.challenge.filerouge_utopios.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class InscriptionController {
    private UserService userService;

    @Autowired
    public InscriptionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/inscription")
    public String inscription(@Valid @ModelAttribute("user") User user,
                              BindingResult bindingResult,
                              Model model) throws IOException {

//        model.addAttribute("connected", loginService.isLogged());
        if (bindingResult.hasErrors()) {
            return "inscription-form";
        } else {
            userService.save(user);
            return "redirect:/login";
        }
    }
}
