package gr.hua.dit.ds.petadoption.services;

import gr.hua.dit.ds.petadoption.dao.PetDao;
import gr.hua.dit.ds.petadoption.entities.Pet;
import gr.hua.dit.ds.petadoption.entities.enums.PetSize;
import gr.hua.dit.ds.petadoption.entities.enums.PetStatus;
import gr.hua.dit.ds.petadoption.entities.enums.Species;
import gr.hua.dit.ds.petadoption.repositories.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepo;
    private final PetDao petDao;

    public PetService(PetRepository petRepo, PetDao petDao) {
        this.petRepo = petRepo;
        this.petDao = petDao;
    }

    public List<Pet> findAll() { return petRepo.findAll(); }
    public Pet findById(Long id) { return petRepo.findById(id).orElse(null); }
    public List<Pet> findPublished() { return petRepo.findByStatus(PetStatus.PUBLISHED); }

    public List<Pet> search(String species, Integer minAgeMonths, Integer maxAgeMonths, String size) {
        return petDao.search(
                species != null ? Species.valueOf(species) : null, // convert String -> Enum
                null, // breed
                null, // sex
                size != null ? PetSize.valueOf(size) : null, // convert String -> Enum
                null, // status
                null, // shelterId
                minAgeMonths,
                maxAgeMonths,
                null  // nameLike
        );
    }


    @Transactional
    public Pet save(Pet p) { return petRepo.save(p); }

    @Transactional
    public void delete(Long id) { petRepo.deleteById(id); }
}
