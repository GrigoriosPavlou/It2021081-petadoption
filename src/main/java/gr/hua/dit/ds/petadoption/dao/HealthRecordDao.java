package gr.hua.dit.ds.petadoption.dao;

import gr.hua.dit.ds.petadoption.entities.HealthRecord;

import java.util.Optional;

public interface HealthRecordDao {
    Optional<HealthRecord> findLatestForPet(Long petId);
}
