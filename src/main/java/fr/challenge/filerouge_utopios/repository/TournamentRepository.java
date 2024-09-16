package fr.challenge.filerouge_utopios.repository;


import fr.challenge.filerouge_utopios.entity.Tournament;
import fr.challenge.filerouge_utopios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}