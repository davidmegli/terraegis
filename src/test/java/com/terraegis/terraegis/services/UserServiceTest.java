package com.terraegis.terraegis.services;
import com.terraegis.terraegis.models.User;
import com.terraegis.terraegis.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

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
        MockitoAnnotations.openMocks(this);
        user = new User(name, description, email, passwordHash, type, role, registrationDate, bio, profilePictureUrl);
    }

    @Test
    void testRegisterUser() {
        when(userRepository.save(user)).thenReturn(user);
        User registeredUser = userService.registerUser(user);
        assertEquals(name, registeredUser.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testLoginUser() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        User loggedInUser = userService.loginUser(email, passwordHash);
        assertNotNull(loggedInUser);
        assertEquals(name, loggedInUser.getName());
    }

    @Test
    void findProjectsByCreatorId() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void loginUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getFundingsByUserId() {
    }

    @Test
    void getCampaignsByUserId() {
    }

    @Test
    void getFinancingProjectsByUserId() {
    }

    @Test
    void getEquitiesByUserId() {
    }

    @Test
    void getDonationsByUserId() {
    }

    @Test
    void getRewardsByUserId() {
    }
}