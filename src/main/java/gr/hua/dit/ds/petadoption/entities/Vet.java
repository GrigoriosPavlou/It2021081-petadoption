package gr.hua.dit.ds.petadoption.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = true, length = 64)
    private String licenseNo;

    private String clinicName;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Vet() {
    }

    public Vet(User user, String licenseNo) {
        this.user = user;
        this.licenseNo = licenseNo;
    }

    public Vet(User user, String licenseNo, String clinicName, String phone) {
        this.user = user;
        this.licenseNo = licenseNo;
        this.clinicName = clinicName;
        this.phone = phone;
    }
}
