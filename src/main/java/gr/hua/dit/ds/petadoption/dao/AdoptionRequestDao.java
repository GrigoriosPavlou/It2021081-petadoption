package gr.hua.dit.ds.petadoption.dao;

import java.util.Map;

public interface AdoptionRequestDao {
    // Επιστρέφει πλήθος αιτήσεων ανά status για ένα shelter
    Map<String, Long> countByStatusForShelter(Long shelterId);
}

