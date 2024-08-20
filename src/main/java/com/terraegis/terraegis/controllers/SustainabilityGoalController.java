package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.SustainabilityGoal;
import com.terraegis.terraegis.services.SustainabilityGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sustainability-goals")
public class SustainabilityGoalController {

    @Autowired
    private SustainabilityGoalService sustainabilityGoalService;

    // get all sustainability goals
    @GetMapping("/all")
    public ResponseEntity<List<SustainabilityGoal>> getAllSustainabilityGoals() {
        Optional<List<SustainabilityGoal>> sustainabilityGoals = sustainabilityGoalService.getAllSustainabilityGoals();
        return sustainabilityGoals.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get a sustainability goal by ID
    @GetMapping("/{id}")
    public ResponseEntity<SustainabilityGoal> getSustainabilityGoalById(@PathVariable Long id) {
        Optional<SustainabilityGoal> sustainabilityGoal = sustainabilityGoalService.getSustainabilityGoalById(id);
        return sustainabilityGoal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get a sustainability goal by number
    @GetMapping("/number/{number}")
    public ResponseEntity<SustainabilityGoal> getSustainabilityGoalByNumber(@PathVariable Long number) {
        Optional<SustainabilityGoal> sustainabilityGoal = sustainabilityGoalService.getSustainabilityGoalByNumber(number);
        return sustainabilityGoal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get a sustainability goal by name
    @GetMapping("/name/{name}")
    public ResponseEntity<SustainabilityGoal> getSustainabilityGoalByName(@PathVariable String name) {
        Optional<SustainabilityGoal> sustainabilityGoal = sustainabilityGoalService.getSustainabilityGoalByName(name);
        return sustainabilityGoal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
