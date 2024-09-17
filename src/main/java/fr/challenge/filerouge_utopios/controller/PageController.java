package fr.challenge.filerouge_utopios.controller;

import fr.challenge.filerouge_utopios.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    //    @Autowired
    public PageController() {
        // ici les init des service entre autre et decommenter l'anotation
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/profil")
    public String profil() {
//        if (loginService.isLogged()) {
//            model.addAttribute("pseudo", loginService.getPseudo());
//            return "profil";
//        }
        return "redirect:/login";
    }

    @RequestMapping("/tournaments")
    public String tournaments() {
//        if (loginService.isLogged()) {
//            model.addAttribute("pseudo", loginService.getPseudo());
//            return "tournaments";
//        }
        return "redirect:/login";
    }

    @RequestMapping("/tournament/id={idTournament}")
    public String tournaments(@PathVariable int idTournament, Model model) {
        //------- logique pour trouver un tournament par id -------//
        //  Tournament tournamentFind tournamentService.getTournamentById(id)  //
        //  if (tournamentFind != null){
        //      model.addAttribute("tournament", tournamentFind);
        //      return "tournament";
        //  }
        //
        //
        return "redirect:/login";
    }

    @RequestMapping("/inscription")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "inscription-form";
    }


}
