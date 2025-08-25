package gr.hua.dit.ds.petadoption.services;

import gr.hua.dit.ds.petadoption.entities.User;
import gr.hua.dit.ds.petadoption.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> findAll() { return repo.findAll(); }
    public User findById(Long id) { return repo.findById(id).orElse(null); }

    @Transactional
    public User save(User u) { return repo.save(u); }

    @Transactional
    public void delete(Long id) { repo.deleteById(id); }
}
