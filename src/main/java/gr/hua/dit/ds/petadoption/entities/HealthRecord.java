package gr.hua.dit.ds.petadoption.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "health_records")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean vaccinated;
    private Boolean neutered;

    @Column(length = 2000)
    private String notes;

    @Column(nullable = false)
    private LocalDateTime recordDate = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vet_user_id")
    private User vetUser; // ο λογαριασμός με ρόλο VET


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Boolean getNeutered() {
        return neutered;
    }

    public void setNeutered(Boolean neutered) {
        this.neutered = neutered;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getVetUser() {
        return vetUser;
    }

    public void setVetUser(User vetUser) {
        this.vetUser = vetUser;
    }

    public HealthRecord() {
    }

    public HealthRecord(LocalDateTime recordDate, Pet pet, User vetUser) {
        this.recordDate = recordDate;
        this.pet = pet;
        this.vetUser = vetUser;
    }

    public HealthRecord(Boolean vaccinated, Boolean neutered, String notes, LocalDateTime recordDate, Pet pet, User vetUser) {
        this.vaccinated = vaccinated;
        this.neutered = neutered;
        this.notes = notes;
        this.recordDate = recordDate;
        this.pet = pet;
        this.vetUser = vetUser;
    }
}


