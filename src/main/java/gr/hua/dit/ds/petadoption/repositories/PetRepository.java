package gr.hua.dit.ds.petadoption.repositories;

import gr.hua.dit.ds.petadoption.entities.Pet;
import gr.hua.dit.ds.petadoption.entities.enums.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByStatus(PetStatus status);
    List<Pet> findByShelterId(Long shelterId);
}
