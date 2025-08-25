package gr.hua.dit.ds.petadoption.dao;

import gr.hua.dit.ds.petadoption.entities.HealthRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class HealthRecordDaoImpl implements HealthRecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<HealthRecord> findLatestForPet(Long petId) {
        String jpql = """
            select hr
            from HealthRecord hr
            where hr.pet.id = :pid
            order by hr.recordDate desc, hr.id desc
        """;
        var list = em.createQuery(jpql, HealthRecord.class)
                .setParameter("pid", petId)
                .setMaxResults(1)
                .getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }
}
