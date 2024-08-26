package com.terraegis.terraegis.controllers;
import com.terraegis.terraegis.models.User;
import com.terraegis.terraegis.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.junit.jupiter.api.BeforeEach;

class UserControllerTest {

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
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    void testRegisterUser() throws Exception {
        when(userService.registerUser(user)).thenReturn(user);

        MvcResult result = mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                //adding content to the request body, User as JSON
                        .content("{\"name\":\"David Megli\",\"description\":\"Software Engineer\",\"email\":\"david@example.com\",\"passwordHash\":\"hashedpassword123\",\"type\":\"Person\",\"role\":\"user\",\"registrationDate\":\"2021-09-01T00:00:00\",\"bio\":\"Experienced software developer with passion for entrepreneurship.\",\"profilePictureUrl\":\"http://example.com/profile.jpg\"}"))
                .andExpect(status().isOk())
                .andReturn();
                //.andExpect(jsonPath("$.name").value(name));
        //String content = result.getResponse().getContentAsString();
        //System.out.println("Response content: "+ content);
    }

    @BeforeEach
    void setUp() {
        userController = new UserController();
        userService = new UserService();
        // Initialize mocks and inject them
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User(name, description, email, passwordHash, type, role, registrationDate, bio, profilePictureUrl);
    }

    @Test
    void registerUser() {
    }

    @Test
    void loginUser() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserByEmail() {
    }

    @Test
    void getProjectsByCreatorId() {
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
    void getRewardsByUserId() {
    }

    @Test
    void getDonationsByUserId() {
    }

    @Test
    void getEquitiesByUserId() {
    }
}