package gr.hua.dit.ds.petadoption.services;

import gr.hua.dit.ds.petadoption.entities.Vet;
import gr.hua.dit.ds.petadoption.repositories.VetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VetService {

    private final VetRepository repo;

    public VetService(VetRepository repo) { this.repo = repo; }

    public List<Vet> findAll() { return repo.findAll(); }
    public Vet findById(Long id) { return repo.findById(id).orElse(null); }

    @Transactional
    public Vet save(Vet v) { return repo.save(v); }

    @Transactional
    public void delete(Long id) { repo.deleteById(id); }
}
