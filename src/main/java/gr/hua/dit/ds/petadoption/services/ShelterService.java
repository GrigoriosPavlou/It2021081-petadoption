package gr.hua.dit.ds.petadoption.services;

import gr.hua.dit.ds.petadoption.entities.Shelter;
import gr.hua.dit.ds.petadoption.repositories.ShelterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShelterService {

    private final ShelterRepository repo;

    public ShelterService(ShelterRepository repo) {
        this.repo = repo;
    }

    public List<Shelter> findAll() { return repo.findAll(); }
    public Shelter findById(Long id) { return repo.findById(id).orElse(null); }

    @Transactional
    public Shelter save(Shelter s) { return repo.save(s); }

    @Transactional
    public void delete(Long id) { repo.deleteById(id); }
}
