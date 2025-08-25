package gr.hua.dit.ds.petadoption.entities;

import gr.hua.dit.ds.petadoption.entities.enums.RequestStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_requests")
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private RequestStatus status = RequestStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime decidedAt;

    @Column(length = 2000)
    private String message;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "adopter_user_id")
    private User adopterUser; // ρόλος ADOPTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDecidedAt() {
        return decidedAt;
    }

    public void setDecidedAt(LocalDateTime decidedAt) {
        this.decidedAt = decidedAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getAdopterUser() {
        return adopterUser;
    }

    public void setAdopterUser(User adopterUser) {
        this.adopterUser = adopterUser;
    }

    public AdoptionRequest() {
    }

    public AdoptionRequest(RequestStatus status, LocalDateTime createdAt, Pet pet, User adopterUser) {
        this.status = status;
        this.createdAt = createdAt;
        this.pet = pet;
        this.adopterUser = adopterUser;
    }

    public AdoptionRequest(Long id, RequestStatus status, LocalDateTime createdAt, String message, Pet pet, User adopterUser) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.message = message;
        this.pet = pet;
        this.adopterUser = adopterUser;
    }
}
