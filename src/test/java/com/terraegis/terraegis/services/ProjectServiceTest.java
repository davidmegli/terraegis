package com.terraegis.terraegis.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.terraegis.terraegis.repositories.ProjectRepository;
import com.terraegis.terraegis.models.Project;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

class ProjectServiceTest {

    // Mocking the repository
    @Mock
    private ProjectRepository projectRepository;

    // Injecting the mocked repository into the service
    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        // Initialize the mocks, could also use @ExtendWith(MockitoExtension.class)
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        Project project = new Project();
        project.setTitle("Sustainable Energy");

        // Mocking the save method of the repository
        when(projectRepository.save(project)).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        assertNotNull(createdProject);
        assertEquals("Sustainable Energy", createdProject.getTitle());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void testGetProjectById() {
        Project project = new Project();
        project.setId(1L);
        project.setTitle("Clean Water");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> foundProject = projectService.getProjectById(1L);

        assertNotNull(foundProject);
        assertTrue(foundProject.isPresent());
        assertEquals(1L, foundProject.get().getId());
        assertEquals("Clean Water", foundProject.get().getTitle());
    }

    @Test
    void getProjectById() {
    }

    @Test
    void getProjectByTitle() {
    }

    @Test
    void getProjectsByCreatorId() {
    }

    @Test
    void getProjectsByCategoryId() {
    }

    @Test
    void getAllProjects() {
    }

    @Test
    void createProject() {
    }

    @Test
    void updateProject() {
    }

    @Test
    void deleteProject() {
    }
}