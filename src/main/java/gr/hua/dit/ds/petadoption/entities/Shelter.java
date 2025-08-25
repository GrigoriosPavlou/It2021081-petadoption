package gr.hua.dit.ds.petadoption.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    private String email;
    private String phone;
    private String address;

    @Column(nullable = false)
    private boolean approved = false;

    // ο λογαριασμός χρήστη που "ανήκει" στο καταφύγιο
    @OneToOne
    @JoinColumn(name = "owner_user_id")
    private User ownerUser;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
    public Shelter() {
    }

    public Shelter(String name, String email, String phone, String address, boolean approved) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.approved = approved;
    }

    public Shelter(String name, String email, String phone, String address, boolean approved, User ownerUser) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.approved = approved;
        this.ownerUser = ownerUser;
    }
}