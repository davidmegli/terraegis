package com.terraegis.terraegis.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name ="type", nullable = false)
    private String type; // Person, Company

    @Column(name = "role", nullable = false) // "user" or "admin"
    private String role;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Column(name = "bio")
    private String bio;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    public User() {
    }

    public User(String name, String description, String email, String passwordHash, String type, String role, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
        this.role = role;
        this.registrationDate = date;
    }

    public User(String name, String description, String email, String passwordHash, String type, String role, LocalDateTime date, String bio, String profilePictureUrl) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
        this.role = role;
        this.registrationDate = date;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
