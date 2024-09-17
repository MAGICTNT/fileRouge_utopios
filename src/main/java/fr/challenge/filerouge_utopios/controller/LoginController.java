
package fr.challenge.filerouge_utopios.controller;

import fr.challenge.filerouge_utopios.entity.User;
import fr.challenge.filerouge_utopios.service.CountryService;
import fr.challenge.filerouge_utopios.service.LoginService;
import fr.challenge.filerouge_utopios.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final LoginService service;
    private final CountryService countryService;
    private final UserService userService;

    public LoginController(LoginService service, CountryService countryService, UserService userService) {
        this.service = service;
        this.countryService = countryService;
        this.userService = userService;
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("countries", countryService.findAll());
        return "inscription-form";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (!service.signup(user)) {
                if (userService.existsByEmail(user.getEmail())) {
                    result.rejectValue("email", "error.user", "Email already exists");
                }
                if (userService.existsByPseudo(user.getPseudo())) {
                    result.rejectValue("pseudo", "error.user", "Pseudo already exists");
                }
            } else {
                service.login(user.getEmail(), user.getPassword());
                return "redirect:/";
            }
        }

        model.addAttribute("countries", countryService.findAll());
        return "inscription-form";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (service.login(email, password)) {
            return "redirect:/";
        }

        model.addAttribute("error", "Email or password is incorrect");
        return "login";

//
//        if (!result.hasErrors()) {
//            if (!service.login(user.getEmail(), user.getPassword())) {
//                result.rejectValue("password", "error.user", "Username or password is incorrect");
//            } else return "redirect:/";
//        }
    }

    @RequestMapping("/logout")
    public String logout() {
        service.logout();
        return "redirect:/";
//        throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPOST() {
        service.logout();
        return "redirect:/";
    }
}
