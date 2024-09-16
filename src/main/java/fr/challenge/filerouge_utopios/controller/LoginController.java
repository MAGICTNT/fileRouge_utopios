package fr.challenge.filerouge_utopios.controller;

import fr.challenge.filerouge_utopios.entity.User;
import fr.challenge.filerouge_utopios.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class LoginController {
    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (!result.hasErrors()) {
            if (!service.signup(user)) {
                result.rejectValue("username", "error.user", "Email or pseudo already exists");
            } else {
                service.login(user.getEmail(), user.getPassword());
                return "redirect:/";
            }
        }
        return "signup";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (!result.hasErrors()) {
            if (!service.login(user.getEmail(), user.getPassword())) {
                result.rejectValue("password", "error.user", "Username or password is incorrect");
            } else return "redirect:/";
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPOST() {
        service.logout();
        return "redirect:/";
    }
}
