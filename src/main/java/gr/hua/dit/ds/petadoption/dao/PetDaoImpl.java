package gr.hua.dit.ds.petadoption.dao;

import gr.hua.dit.ds.petadoption.entities.Pet;
import gr.hua.dit.ds.petadoption.entities.enums.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PetDaoImpl implements PetDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Pet> search(Species species,
                            String breed,
                            Sex sex,
                            PetSize size,
                            PetStatus status,
                            Long shelterId,
                            Integer minAgeMonths,
                            Integer maxAgeMonths,
                            String nameLike) {

        StringBuilder jpql = new StringBuilder("select p from Pet p where 1=1");
        Map<String, Object> params = new HashMap<>();

        if (species != null) {
            jpql.append(" and p.species = :species");
            params.put("species", species);
        }
        if (breed != null && !breed.isBlank()) {
            jpql.append(" and lower(p.breed) like :breed");
            params.put("breed", "%" + breed.toLowerCase() + "%");
        }
        if (sex != null) {
            jpql.append(" and p.sex = :sex");
            params.put("sex", sex);
        }
        if (size != null) {
            jpql.append(" and p.size = :size");
            params.put("size", size);
        }
        if (status != null) {
            jpql.append(" and p.status = :status");
            params.put("status", status);
        }
        if (shelterId != null) {
            jpql.append(" and p.shelter.id = :shelterId");
            params.put("shelterId", shelterId);
        }
        // λογική με min/max ηλικία σε ΜΗΝΕΣ:
        if (minAgeMonths != null) {
            jpql.append(" and p.maxAgeMonths >= :minAgeMonths");
            params.put("minAgeMonths", minAgeMonths);
        }
        if (maxAgeMonths != null) {
            jpql.append(" and p.minAgeMonths <= :maxAgeMonths");
            params.put("maxAgeMonths", maxAgeMonths);
        }
        if (nameLike != null && !nameLike.isBlank()) {
            jpql.append(" and lower(p.name) like :nameLike");
            params.put("nameLike", "%" + nameLike.toLowerCase() + "%");
        }

        var q = em.createQuery(jpql.toString(), Pet.class);
        params.forEach(q::setParameter);
        return q.getResultList();
    }
}
