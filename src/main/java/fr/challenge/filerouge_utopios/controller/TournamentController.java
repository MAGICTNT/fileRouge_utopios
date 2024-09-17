package fr.challenge.filerouge_utopios.controller;

import fr.challenge.filerouge_utopios.entity.Tournament;
import fr.challenge.filerouge_utopios.service.LoginService;
import fr.challenge.filerouge_utopios.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TournamentController {
    private final LoginService service;
    private final TournamentService tournamentService;
    public TournamentController(final LoginService service,TournamentService tournamentService ) {
        this.service = service;
        this.tournamentService = tournamentService;
    }

    @RequestMapping("/tournaments")
    public String tournament(Model model) {
        for(Tournament tournament : tournamentService.findAll()){
            System.out.println(tournament.getStartDate());
        }
        model.addAttribute("tournaments", tournamentService.findAll());
        return "tournament-list";
    }

}
