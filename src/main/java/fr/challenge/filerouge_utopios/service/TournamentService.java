package fr.challenge.filerouge_utopios.service;

import fr.challenge.filerouge_utopios.entity.Tournament;
import fr.challenge.filerouge_utopios.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository repository;

    public TournamentService(TournamentRepository repository) {
        this.repository = repository;
    }

    public List<Tournament> findAll() {
        return repository.findAll();
    }

    public Tournament findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Tournament save(Tournament tournament) {
        return repository.save(tournament);
    }

    public void delete(Tournament tournament) {
        repository.delete(tournament);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
