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

    public Optional<List<SustainabilityGoal>> getAllSustainabilityGoals() {
        return Optional.of(sustainabilityGoalRepository.findAll());
    }

    public SustainabilityGoal createSustainabilityGoal(SustainabilityGoal sustainabilityGoal) {
        return sustainabilityGoalRepository.save(sustainabilityGoal);
    }

    public Optional<SustainabilityGoal> getSustainabilityGoalByNumber(Long number) {
        return sustainabilityGoalRepository.findByNumber(number);
    }

    public Optional<SustainabilityGoal> getSustainabilityGoalByName(String name) {
        return sustainabilityGoalRepository.findByName(name);
    }

    public SustainabilityGoal updateSustainabilityGoal(Long id, SustainabilityGoal sustainabilityGoalDetails) {
        Optional<SustainabilityGoal> sustainabilityGoal = sustainabilityGoalRepository.findById(id);
        if (sustainabilityGoal.isPresent()) {
            SustainabilityGoal updatedSustainabilityGoal = sustainabilityGoal.get();
            updatedSustainabilityGoal.setId(sustainabilityGoalDetails.getId());
            updatedSustainabilityGoal.setNumber(sustainabilityGoalDetails.getNumber());
            updatedSustainabilityGoal.setName(sustainabilityGoalDetails.getName());
            updatedSustainabilityGoal.setDescription(sustainabilityGoalDetails.getDescription());
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

    public void saveGoalsIfNotExists(List<SustainabilityGoal> goals) {
        for (SustainabilityGoal goal : goals) {
            if (!sustainabilityGoalRepository.existsByNumber(goal.getNumber())) {
                sustainabilityGoalRepository.save(goal);
            }
        }
    }
}
