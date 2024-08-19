package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.SustainabilityGoal;
import com.terraegis.terraegis.repositories.SustainabilityGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class SustainabilityGoalService {

    @Autowired
    private SustainabilityGoalRepository sustainabilityGoalRepository;

    public Optional<SustainabilityGoal> getSustainabilityGoalById(Long id) {
        return sustainabilityGoalRepository.findById(id);
    }

    public Optional<SustainabilityGoal> getSustainabilityGoalByProjectId(Long projectId) {
        return sustainabilityGoalRepository.findByProjectId(projectId);
    }

    public Optional<List<SustainabilityGoal>> getSustainabilityGoalsByUserId(Long userId) {
        return sustainabilityGoalRepository.findSustainabilityGoalsByUserId(userId);
    }

    public Optional<List<SustainabilityGoal>> getAllSustainabilityGoals() {
        return Optional.of(sustainabilityGoalRepository.findAll());
    }

    public SustainabilityGoal createSustainabilityGoal(SustainabilityGoal sustainabilityGoal) {
        return sustainabilityGoalRepository.save(sustainabilityGoal);
    }

    public SustainabilityGoal updateSustainabilityGoal(Long id, SustainabilityGoal sustainabilityGoalDetails) {
        Optional<SustainabilityGoal> sustainabilityGoal = sustainabilityGoalRepository.findById(id);
        if (sustainabilityGoal.isPresent()) {
            SustainabilityGoal updatedSustainabilityGoal = sustainabilityGoal.get();
            updatedSustainabilityGoal.setAmount(sustainabilityGoalDetails.getAmount());
            updatedSustainabilityGoal.setProject(sustainabilityGoalDetails.getProject());
            updatedSustainabilityGoal.setUser(sustainabilityGoalDetails.getUser());
            return sustainabilityGoalRepository.save(updatedSustainabilityGoal);
        } else {
            return null;
        }
    }

    public boolean deleteSustainabilityGoal(Long id) {
        Optional<SustainabilityGoal> sustainabilityGoal = sustainabilityGoalRepository.findById(id);
        if (sustainabilityGoal.isPresent()) {
            sustainabilityGoalRepository.delete(sustainabilityGoal.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<List<SustainabilityGoal>> getSustainabilityGoalsByProjectId(Long projectId) {
        return sustainabilityGoalRepository.findSustainabilityGoalsByProjectId(projectId);
    }

    public Optional<List<SustainabilityGoal>> getSustainabilityGoalsByUserId(Long userId) {
        return sustainabilityGoalRepository.findSustainabilityGoalsByUserId(userId);
    }
}
