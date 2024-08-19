package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.Campaign;
import com.terraegis.terraegis.models.Funding;
import com.terraegis.terraegis.models.Project;
import com.terraegis.terraegis.models.User;
import com.terraegis.terraegis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // create a new user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        Optional<List<User>> users = userService.getAllUsers();
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // update a user
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete a user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get a user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get all projects created by a user
    @GetMapping("/{id}/projects")
    public ResponseEntity<List<User>> getProjectsByCreatorId(@PathVariable Long creatorId) {
        Optional<List<User>> projects = userService.findProjectsByCreatorId(creatorId);
        return projects.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all fundings by a user
    @GetMapping("/{id}/fundings")
    public ResponseEntity<List<Funding>> getFundingsByUserId(@PathVariable Long userId) {
        Optional<List<Funding>> fundings = userService.findFundingsByUserId(userId);
        return fundings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all campaigns by a user
    @GetMapping("/{id}/campaigns")
    public ResponseEntity<List<Campaign>> getCampaignsByUserId(@PathVariable Long userId) {
        Optional<List<Campaign>> campaigns = userService.findCampaignsByUserId(userId);
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all projects by a user
    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Project>> getProjectsByUserId(@PathVariable Long userId) {
        Optional<List<Project>> projects = userService.findProjectsByUserId(userId);
        return projects.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all projects that a user is financing
    @GetMapping("/{id}/financing-projects")
    public ResponseEntity<List<Project>> getFinancingProjectsByUserId(@PathVariable Long userId) {
        Optional<List<Project>> projects = userService.findFinancingProjectsByUserId(userId);
        return projects.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
