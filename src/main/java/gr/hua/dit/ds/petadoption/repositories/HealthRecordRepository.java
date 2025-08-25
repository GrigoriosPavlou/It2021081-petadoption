package gr.hua.dit.ds.petadoption.repositories;

import gr.hua.dit.ds.petadoption.entities.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
}
