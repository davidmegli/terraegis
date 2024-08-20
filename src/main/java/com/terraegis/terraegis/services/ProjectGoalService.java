package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.ProjectGoal;
import com.terraegis.terraegis.repositories.ProjectGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ProjectGoalService {

    @Autowired
    private ProjectGoalRepository projectGoalRepository;

    public Optional<ProjectGoal> getProjectGoalById(Long id) {
        return projectGoalRepository.findById(id);
    }

    public Optional<List<ProjectGoal>> getProjectGoalsByProjectId(Long projectId) {
        return projectGoalRepository.findProjectGoalsByProjectId(projectId);
    }

    public Optional<List<ProjectGoal>> getProjectGoalsByUserId(Long userId) {
        return projectGoalRepository.findProjectGoalsByUserId(userId);
    }

    public Optional<List<ProjectGoal>> getAllProjectGoals() {
        return Optional.of(projectGoalRepository.findAll());
    }

    public ProjectGoal createProjectGoal(ProjectGoal projectGoal) {
        return projectGoalRepository.save(projectGoal);
    }

    public ProjectGoal updateProjectGoal(Long id, ProjectGoal projectGoalDetails) {
        Optional<ProjectGoal> projectGoal = projectGoalRepository.findById(id);
        if (projectGoal.isPresent()) {
            ProjectGoal updatedProjectGoal = projectGoal.get();
            updatedProjectGoal.setId(projectGoalDetails.getId());
            updatedProjectGoal.setProjectId(projectGoalDetails.getProjectId());
            updatedProjectGoal.setSustainabilityGoalId(projectGoalDetails.getSustainabilityGoalId());
            updatedProjectGoal.setDescription(projectGoalDetails.getDescription());
            return projectGoalRepository.save(updatedProjectGoal);
        } else {
            return null;
        }
    }

    public boolean deleteProjectGoal(Long id) {
        Optional<ProjectGoal> projectGoal = projectGoalRepository.findById(id);
        if (projectGoal.isPresent()) {
            projectGoalRepository.delete(projectGoal.get());
            return true;
        } else {
            return false;
        }
    }
}
