package fr.challenge.filerouge_utopios.service;

import fr.challenge.filerouge_utopios.entity.User;
import fr.challenge.filerouge_utopios.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
