package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.Project;
import com.terraegis.terraegis.models.User;
import com.terraegis.terraegis.models.Category;
import com.terraegis.terraegis.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.mockito.MockitoAnnotations;

class ProjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private Project project;

    @BeforeEach
    void setUp() {
        projectController = new ProjectController();
        MockitoAnnotations.openMocks(this); // Initialize mocks
        mockMvc = standaloneSetup(projectController).build();

        User creator = new User();
        creator.setId(1L);
        creator.setName("David Megli");

        Category category = new Category();
        category.setId(1L);
        category.setName("Technology");

        project = new Project();
        project.setId(1L);
        project.setTitle("Project Title");
        project.setDescription("Project Description");
        project.setImageUrl("http://example.com/image.jpg");
        project.setCreator(creator);
        project.setCategory(category);
        project.setValuation(new BigDecimal("100000.00"));
    }

    @Test
    void createProject() throws Exception {
        when(projectService.createProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/api/projects/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Project Title\",\"description\":\"Project Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Project Title"));
    }

    @Test
    void getAllProjects() throws Exception {
        List<Project> projects = new ArrayList<>();
        projects.add(project);

        when(projectService.getAllProjects()).thenReturn(Optional.of(projects));

        mockMvc.perform(get("/api/projects/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Project Title"));
    }

    @Test
    void getProjectById() throws Exception {
        when(projectService.getProjectById(1L)).thenReturn(Optional.of(project));

        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Project Title"));
    }

    @Test
    void updateProject() throws Exception {
        // Creazione di un oggetto Project aggiornato che simula il ritorno dal servizio
        Project updatedProject = new Project();
        updatedProject.setId(1L);
        updatedProject.setTitle("Updated Title");
        updatedProject.setDescription("Updated Description");
        updatedProject.setImageUrl("http://example.com/updatedimage.jpg");
        updatedProject.setCreator(project.getCreator());
        updatedProject.setCategory(project.getCategory());
        updatedProject.setValuation(new BigDecimal("200000.00"));

        // Configura il mock per restituire il progetto aggiornato
        when(projectService.updateProject(eq(1L), any(Project.class))).thenReturn(updatedProject);

        // Esegui la richiesta PUT al controller e verifica il risultato
        mockMvc.perform(put("/api/projects/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"description\":\"Updated Description\",\"imageUrl\":\"http://example.com/updatedimage.jpg\",\"creator\":{\"id\":1},\"category\":{\"id\":1},\"valuation\":200000.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/updatedimage.jpg"))
                .andExpect(jsonPath("$.valuation").value(200000.00));
    }

    @Test
    void deleteProject() throws Exception {
        when(projectService.deleteProject(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/projects/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getProjectsByCreatorId() throws Exception {
        List<Project> projects = new ArrayList<>();
        projects.add(project);

        when(projectService.getProjectsByCreatorId(1L)).thenReturn(Optional.of(projects));

        mockMvc.perform(get("/api/projects/creator/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Project Title"));
    }

    @Test
    void getProjectByTitle() throws Exception {
        when(projectService.getProjectByTitle("Project Title")).thenReturn(Optional.of(project));

        mockMvc.perform(get("/api/projects/title/Project Title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Project Title"));
    }

    @Test
    void getProjectsByCategoryId() throws Exception {
        List<Project> projects = new ArrayList<>();
        projects.add(project);

        when(projectService.getProjectsByCategoryId(1L)).thenReturn(Optional.of(projects));

        mockMvc.perform(get("/api/projects/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Project Title"));
    }
}
