package fr.challenge.filerouge_utopios.service;

import fr.challenge.filerouge_utopios.entity.User;
import fr.challenge.filerouge_utopios.util.enums.AccountLevel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

// TODO: Use Spring Security for authentication if time permits it
@Service
public class LoginService {
    private final UserService userService;
    private final CountryService countryService;
    private final HttpSession httpSession;

    public LoginService(UserService userService, CountryService countryService, HttpSession httpSession) {
        this.userService = userService;
        this.countryService = countryService;
        this.httpSession = httpSession;

        // Create admin user for testing purpose only
        // TODO: DON'T FORGET TO REMOVE THIS IN PRODUCTION
        if (!userService.existsByPseudo("admin")) {
            userService.save(User.builder()
                    .email("admin@admin.admin")
                    .pseudo("admin")
                    .password("admin")
                    .birthDate(LocalDate.now())
                    .accountLevel(AccountLevel.ADMIN)
                    .country(countryService.findByTag("FRA"))
                    .build());
        }
    }

    public boolean signup(User user) {
        if (!userService.existsByEmail(user.getEmail()) && !userService.existsByPseudo(user.getPseudo())) {
            userService.save(user);
            return true;
        }
        return false;
    }

    public boolean login(String email, String password) {
        if (!userService.existsByEmail(email)) return false;

        User user = userService.findByEmail(email);
        if (user.getPassword().equals(password)) {
            httpSession.setAttribute("isLoggedIn", true);
            httpSession.setAttribute("user", user);
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        try {
            return (boolean) httpSession.getAttribute("isLoggedIn");
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public void logout() {
        httpSession.removeAttribute("isLoggedIn");
        httpSession.removeAttribute("user");
    }
}
