package gr.hua.dit.ds.petadoption.dao;

import gr.hua.dit.ds.petadoption.entities.Pet;
import gr.hua.dit.ds.petadoption.entities.enums.PetSize;
import gr.hua.dit.ds.petadoption.entities.enums.PetStatus;
import gr.hua.dit.ds.petadoption.entities.enums.Sex;
import gr.hua.dit.ds.petadoption.entities.enums.Species;

import java.util.List;
    public interface PetDao {
        List<Pet> search(
                Species species,      // p.species
                String breed,         // p.breed  (LIKE)
                Sex sex,              // p.sex
                PetSize size,         // p.size
                PetStatus status,     // p.status
                Long shelterId,       // p.shelter.id
                Integer minAgeMonths, // p.maxAgeMonths >= minAgeMonths
                Integer maxAgeMonths, // p.minAgeMonths <= maxAgeMonths
                String nameLike       // p.name LIKE
        );
    }


