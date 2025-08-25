package gr.hua.dit.ds.petadoption.services;

import gr.hua.dit.ds.petadoption.dao.HealthRecordDao;
import gr.hua.dit.ds.petadoption.entities.HealthRecord;
import gr.hua.dit.ds.petadoption.repositories.HealthRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HealthRecordService {

    private final HealthRecordRepository repo;
    private final HealthRecordDao dao;

    public HealthRecordService(HealthRecordRepository repo, HealthRecordDao dao) {
        this.repo = repo;
        this.dao = dao;
    }

    public Optional<HealthRecord> latestForPet(Long petId) {
        return dao.findLatestForPet(petId);
    }

    @Transactional
    public HealthRecord save(HealthRecord hr) {
        return repo.save(hr);
    }
}
