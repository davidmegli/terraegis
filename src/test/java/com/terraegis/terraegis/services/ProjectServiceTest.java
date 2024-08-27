package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.Project;
import com.terraegis.terraegis.models.User;
import com.terraegis.terraegis.models.Category;
import com.terraegis.terraegis.repositories.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private User creator;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        creator = new User();
        creator.setId(1L);
        creator.setName("David Megli");

        category = new Category();
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
    void getProjectById() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> foundProject = projectService.getProjectById(1L);
        assertTrue(foundProject.isPresent());
        assertEquals("Project Title", foundProject.get().getTitle());
    }

    @Test
    void getProjectByTitle() {
        when(projectRepository.findByTitle("Project Title")).thenReturn(Optional.of(project));

        Optional<Project> foundProject = projectService.getProjectByTitle("Project Title");
        assertTrue(foundProject.isPresent());
        assertEquals("Project Title", foundProject.get().getTitle());
    }

    @Test
    void getProjectsByCreatorId() {
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        when(projectRepository.findProjectsByCreatorId_Id(1L)).thenReturn(Optional.of(projects));

        Optional<List<Project>> foundProjects = projectService.getProjectsByCreatorId(1L);
        assertTrue(foundProjects.isPresent());
        assertEquals(1, foundProjects.get().size());
        assertEquals("Project Title", foundProjects.get().get(0).getTitle());
    }

    @Test
    void getProjectsByCategoryId() {
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        when(projectRepository.findProjectsByCategoryId_Id(1L)).thenReturn(Optional.of(projects));

        Optional<List<Project>> foundProjects = projectService.getProjectsByCategoryId(1L);
        assertTrue(foundProjects.isPresent());
        assertEquals(1, foundProjects.get().size());
        assertEquals("Project Title", foundProjects.get().get(0).getTitle());
    }

    @Test
    void getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        when(projectRepository.findAll()).thenReturn(projects);

        Optional<List<Project>> foundProjects = projectService.getAllProjects();
        assertTrue(foundProjects.isPresent());
        assertEquals(1, foundProjects.get().size());
        assertEquals("Project Title", foundProjects.get().get(0).getTitle());
    }

    @Test
    void createProject() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);
        assertEquals("Project Title", createdProject.getTitle());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    void updateProject() {
        Project updatedDetails = new Project();
        updatedDetails.setTitle("Updated Title");
        updatedDetails.setDescription("Updated Description");
        updatedDetails.setImageUrl("http://example.com/updatedimage.jpg");
        updatedDetails.setCreator(creator);
        updatedDetails.setCategory(category);
        updatedDetails.setValuation(new BigDecimal("200000.00"));

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(updatedDetails);

        Project updatedProject = projectService.updateProject(1L, updatedDetails);
        assertEquals("Updated Title", updatedProject.getTitle());
        assertEquals("Updated Description", updatedProject.getDescription());
    }

    @Test
    void deleteProject() {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        boolean isDeleted = projectService.deleteProject(1L);
        assertTrue(isDeleted);
        verify(projectRepository, times(1)).delete(project);
    }

    @Test
    void deleteProject_NotFound() {
        when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        boolean isDeleted = projectService.deleteProject(1L);
        assertFalse(isDeleted);
    }
}
