package gr.hua.dit.ds.petadoption.repositories;

import gr.hua.dit.ds.petadoption.entities.AdoptionRequest;
import gr.hua.dit.ds.petadoption.entities.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {
    List<AdoptionRequest> findByAdopterUserId(Long userId);
    List<AdoptionRequest> findByPetShelterId(Long shelterId);
    List<AdoptionRequest> findByStatus(RequestStatus status);
}
