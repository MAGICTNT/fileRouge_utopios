package fr.challenge.filerouge_utopios.controller;

import fr.challenge.filerouge_utopios.entity.Tournament;
import fr.challenge.filerouge_utopios.service.LoginService;
import fr.challenge.filerouge_utopios.service.TournamentService;
import fr.challenge.filerouge_utopios.util.enums.Format;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/newTournament")
    public String newTournament(Model model) {
        model.addAttribute("tournament", new Tournament());
        model.addAttribute("formats", Format.values());
        model.addAttribute("statusMessage", null);
        return "tournament-create-update";
    }

    @RequestMapping(value = "/newTournament", method = RequestMethod.POST)
    public String newTournament(@Valid @ModelAttribute("tournament") Tournament tournament, BindingResult result, Model model) {
        tournament.setMinElo(0);
//        tournament.setFormat(Format.values()[0]);
        Tournament newTournament = tournamentService.save(tournament);
        model.addAttribute("tournament", new Tournament());
        model.addAttribute("formats", Format.values());
        model.addAttribute("statusMessage", "tournament " + tournament.getLabel() + " created");
        return "/tournament-create-update";
    }



}
