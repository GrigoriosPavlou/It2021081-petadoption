package gr.hua.dit.ds.petadoption.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdoptionRequestDaoImpl implements AdoptionRequestDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Map<String, Long> countByStatusForShelter(Long shelterId) {
        String jpql = """
            select ar.status, count(ar)
            from AdoptionRequest ar
            where ar.pet.shelter.id = :sid
            group by ar.status
            order by ar.status
        """;
        List<Object[]> rows = em.createQuery(jpql, Object[].class)
                .setParameter("sid", shelterId)
                .getResultList();

        Map<String, Long> map = new LinkedHashMap<>();
        for (Object[] r : rows) {
            map.put(String.valueOf(r[0]), (Long) r[1]);
        }
        return map;
    }
}
