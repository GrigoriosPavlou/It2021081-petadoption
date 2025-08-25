package gr.hua.dit.ds.petadoption.entities;

import gr.hua.dit.ds.petadoption.entities.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 24)
    private Species species;

    @Column(length = 64)
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Sex sex = Sex.UNKNOWN;

    private Integer minAgeMonths;
    private Integer maxAgeMonths;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private PetSize size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PetStatus status = PetStatus.DRAFT;

    @Column(length = 2000)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ---- Constructors ----
    public Pet() {}

    // Constructor με βασικά
    public Pet(String name, Species species, Shelter shelter) {
        this.name = name;
        this.species = species;
        this.shelter = shelter;
        this.status = PetStatus.DRAFT;
    }

    // Constructor με όλα
    public Pet(String name, Species species, String breed, Sex sex,
               Integer minAgeMonths, Integer maxAgeMonths,
               PetSize size, PetStatus status, String description, Shelter shelter) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.minAgeMonths = minAgeMonths;
        this.maxAgeMonths = maxAgeMonths;
        this.size = size;
        this.status = status;
        this.description = description;
        this.shelter = shelter;
    }


    // ---- Getters & Setters ----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getMinAgeMonths() {
        return minAgeMonths;
    }

    public void setMinAgeMonths(Integer minAgeMonths) {
        this.minAgeMonths = minAgeMonths;
    }

    public Integer getMaxAgeMonths() {
        return maxAgeMonths;
    }

    public void setMaxAgeMonths(Integer maxAgeMonths) {
        this.maxAgeMonths = maxAgeMonths;
    }

    public PetSize getSize() {
        return size;
    }

    public void setSize(PetSize size) {
        this.size = size;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
