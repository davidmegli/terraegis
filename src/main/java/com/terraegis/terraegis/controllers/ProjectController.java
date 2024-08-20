package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.Project;
import com.terraegis.terraegis.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // create a new project
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }

    // get all projects
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProjects() {
        Optional<List<Project>> projects = projectService.getAllProjects();
        return projects.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get a project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // update a project
    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Project updatedProject = projectService.updateProject(id, projectDetails);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete a project
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        boolean isDeleted = projectService.deleteProject(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get projects by creator ID
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<Project>> getProjectsByCreatorId(@PathVariable Long creatorId) {
        Optional<List<Project>> projects = projectService.getProjectsByCreatorId(creatorId);
        return projects.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get projects by title
    @GetMapping("/title/{title}")
    public ResponseEntity<Project> getProjectByTitle(@PathVariable String title) {
        Optional<Project> project = projectService.getProjectByTitle(title);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get projects by category ID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Project>> getProjectsByCategoryId(@PathVariable Long categoryId) {
        Optional<List<Project>> projects = projectService.getProjectsByCategoryId(categoryId);
        return projects.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}