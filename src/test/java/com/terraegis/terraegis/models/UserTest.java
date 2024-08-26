package com.terraegis.terraegis.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private final Long id = 1L;
    private final String name = "David Megli";
    private final String description = "Software Engineer";
    private final String email = "david@example.com";
    private final String passwordHash = "hashedpassword123";
    private final String type = "Person";
    private final String role = "user";
    private final LocalDateTime registrationDate = LocalDateTime.now();
    private final String bio = "Experienced software developer with passion for entrepreneurship.";
    private final String profilePictureUrl = "http://example.com/profile.jpg";

    @BeforeEach
    void setUp() {
        user = new User(name, description, email, passwordHash, type, role, registrationDate, bio, profilePictureUrl);
        user.setId(id);
    }

    @Test
    void getId() {
        assertEquals(id, user.getId());
    }

    @Test
    void setId() {
        Long newId = 2L;
        user.setId(newId);
        assertEquals(newId, user.getId());
    }

    @Test
    void getName() {
        assertEquals(name, user.getName());
    }

    @Test
    void setName() {
        String newName = "Jane Doe";
        user.setName(newName);
        assertEquals(newName, user.getName());
    }

    @Test
    void getDescription() {
        assertEquals(description, user.getDescription());
    }

    @Test
    void setDescription() {
        String newDescription = "Senior Developer";
        user.setDescription(newDescription);
        assertEquals(newDescription, user.getDescription());
    }

    @Test
    void getEmail() {
        assertEquals(email, user.getEmail());
    }

    @Test
    void setEmail() {
        String newEmail = "bob@example.com";
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    void getPasswordHash() {
        assertEquals(passwordHash, user.getPasswordHash());
    }

    @Test
    void setPasswordHash() {
        String newPasswordHash = "newhashedpassword456";
        user.setPasswordHash(newPasswordHash);
        assertEquals(newPasswordHash, user.getPasswordHash());
    }

    @Test
    void getType() {
        assertEquals(type, user.getType());
    }

    @Test
    void setType() {
        String newType = "Company";
        user.setType(newType);
        assertEquals(newType, user.getType());
    }

    @Test
    void getRole() {
        assertEquals(role, user.getRole());
    }

    @Test
    void setRole() {
        String newRole = "admin";
        user.setRole(newRole);
        assertEquals(newRole, user.getRole());
    }

    @Test
    void getRegistrationDate() {
        assertEquals(registrationDate, user.getRegistrationDate());
    }

    @Test
    void setRegistrationDate() {
        LocalDateTime newDate = LocalDateTime.now().minusDays(1);
        user.setRegistrationDate(newDate);
        assertEquals(newDate, user.getRegistrationDate());
    }

    @Test
    void getBio() {
        assertEquals(bio, user.getBio());
    }

    @Test
    void setBio() {
        String newBio = "Updated bio";
        user.setBio(newBio);
        assertEquals(newBio, user.getBio());
    }

    @Test
    void getProfilePictureUrl() {
        assertEquals(profilePictureUrl, user.getProfilePictureUrl());
    }

    @Test
    void setProfilePictureUrl() {
        String newProfilePictureUrl = "http://example.com/newprofile.jpg";
        user.setProfilePictureUrl(newProfilePictureUrl);
        assertEquals(newProfilePictureUrl, user.getProfilePictureUrl());
    }
}