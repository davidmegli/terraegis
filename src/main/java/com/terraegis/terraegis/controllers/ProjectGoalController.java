package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.ProjectGoal;
import com.terraegis.terraegis.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project-goals")
public class ProjectGoalController {

    @Autowired
    private ProjectGoalService projectGoalService;

    // create a new project goal
    @PostMapping("/create")
    public ResponseEntity<ProjectGoal> createProjectGoal(@RequestBody ProjectGoal projectGoal) {
        ProjectGoal createdProjectGoal = projectGoalService.createProjectGoal(projectGoal);
        return ResponseEntity.ok(createdProjectGoal);
    }

    // get all project goals
    @GetMapping("/all")
    public ResponseEntity<List<ProjectGoal>> getAllProjectGoals() {
        Optional<List<ProjectGoal>> projectGoals = projectGoalService.getAllProjectGoals();
        return projectGoals.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get a project goal by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjectGoal> getProjectGoalById(@PathVariable Long id) {
        Optional<ProjectGoal> projectGoal = projectGoalService.getProjectGoalById(id);
        return projectGoal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // update a project goal
    @PutMapping("/update/{id}")
    public ResponseEntity<ProjectGoal> updateProjectGoal(@PathVariable Long id, @RequestBody ProjectGoal projectGoalDetails) {
        ProjectGoal updatedProjectGoal = projectGoalService.updateProjectGoal(id, projectGoalDetails);
        if (updatedProjectGoal != null) {
            return ResponseEntity.ok(updatedProjectGoal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete a project goal
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProjectGoal(@PathVariable Long id) {
        boolean isDeleted = projectGoalService.deleteProjectGoal(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get all project goals by project ID
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectGoal>> getProjectGoalsByProjectId(@PathVariable Long projectId) {
        Optional<List<ProjectGoal>> projectGoals = projectGoalService.getProjectGoalsByProjectId(projectId);
        return projectGoals.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


}
