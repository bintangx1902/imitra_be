package com.postgre.springapipostgre.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 150)
    private String username;

    @Column(unique = true, nullable = false, length = 254)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 150)
    private String lastName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "is_staff", nullable = false)
    private boolean isStaff = false;

    @Column(name = "is_superuser", nullable = false)
    private boolean isSuperuser = false;

    private boolean isManager = false;
    private boolean isVp = false;
    private boolean isDirector = false;
    private boolean isPartnershipManager = false;
    private boolean isPartnershipStaff = false;

    // Constructors
    public User() {
    }

    public User(String username, String email, String password, String firstName, String lastName, boolean isManager,
                boolean isVp, boolean isDirector, boolean isPartnershipManager, boolean isPartnershipStaff) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isManager = isManager;
        this.isVp = isVp;
        this.isDirector = isDirector;
        this.isPartnershipManager = isPartnershipManager;
        this.isPartnershipStaff = isPartnershipStaff;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public boolean isSuperuser() {
        return isSuperuser;
    }

    public void setSuperuser(boolean superuser) {
        isSuperuser = superuser;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isVp() {
        return isVp;
    }

    public void setVp(boolean vp) {
        isVp = vp;
    }

    public boolean isDirector() {
        return isDirector;
    }

    public void setDirector(boolean director) {
        isDirector = director;
    }

    public boolean isPartnershipManager() {
        return isPartnershipManager;
    }

    public void setPartnershipManager(boolean partnershipManager) {
        isPartnershipManager = partnershipManager;
    }

    public boolean isPartnershipStaff() {
        return isPartnershipStaff;
    }

    public void setPartnershipStaff(boolean partnershipStaff) {
        isPartnershipStaff = partnershipStaff;
    }

}
