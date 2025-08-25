package gr.hua.dit.ds.petadoption.services;

import gr.hua.dit.ds.petadoption.dao.AdoptionRequestDao;
import gr.hua.dit.ds.petadoption.entities.AdoptionRequest;
import gr.hua.dit.ds.petadoption.entities.enums.RequestStatus;
import gr.hua.dit.ds.petadoption.repositories.AdoptionRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AdoptionRequestService {

    private final AdoptionRequestRepository repo;
    private final AdoptionRequestDao dao;

    public AdoptionRequestService(AdoptionRequestRepository repo, AdoptionRequestDao dao) {
        this.repo = repo;
        this.dao = dao;
    }

    public List<AdoptionRequest> findAll() { return repo.findAll(); }
    public AdoptionRequest findById(Long id) { return repo.findById(id).orElse(null); }

    public List<AdoptionRequest> findByAdopter(Long userId) {
        return repo.findByAdopterUserId(userId);
    }

    public List<AdoptionRequest> findByStatus(RequestStatus status) {
        return repo.findByStatus(status);
    }

    public Map<String, Long> countByStatusForShelter(Long shelterId) {
        return dao.countByStatusForShelter(shelterId);
    }

    @Transactional
    public AdoptionRequest save(AdoptionRequest ar) { return repo.save(ar); }

    @Transactional
    public void delete(Long id) { repo.deleteById(id); }
}
