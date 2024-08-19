package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.Project;
import com.terraegis.terraegis.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Optional<Project> getProjectByTitle(String title) {
        return projectRepository.findByTitle(title);
    }

    public Optional<List<Project>> getProjectsByCreatorId(Long creatorId) {
        return projectRepository.findProjectsByCreatorId(creatorId);
    }

    public Optional<List<Project>> getAllProjects() {
        return Optional.of(projectRepository.findAll());
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            Project updatedProject = project.get();
            updatedProject.setTitle(projectDetails.getTitle());
            updatedProject.setDescription(projectDetails.getDescription());
            updatedProject.setImageUrl(projectDetails.getImageUrl());
            updatedProject.setCreator(projectDetails.getCreator());
            updatedProject.setCategory(projectDetails.getCategory());
            updatedProject.setValuation(projectDetails.getValuation());
            return projectRepository.save(updatedProject);
        } else {
            return null;
        }
    }

    public boolean deleteProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return true;
        } else {
            return false;
        }
    }
}