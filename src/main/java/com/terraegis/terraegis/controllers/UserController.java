package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.*;
import com.terraegis.terraegis.services.UserService;
import com.terraegis.terraegis.services.ProjectService;
import com.terraegis.terraegis.services.FundingService;
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

    @Autowired
    private ProjectService projectService;

    @Autowired
    private FundingService fundingService;

    // create a new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User createdUser = userService.registerUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // login a user
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody String user, @RequestBody String password) {
        User loggedInUser = userService.loginUser(user, password);
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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
    public ResponseEntity<List<Project>> getProjectsByCreatorId(@PathVariable Long creatorId) {
        Optional<List<Project>> projects = projectService.getProjectsByCreatorId(creatorId);
        return projects.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all fundings by a user
    @GetMapping("/{id}/fundings")
    public ResponseEntity<List<Funding>> getFundingsByUserId(@PathVariable Long userId) {
        Optional<List<Funding>> fundings = fundingService.getFundingsByUserId(userId);
        return fundings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all campaigns by a user
    @GetMapping("/{id}/campaigns")
    public ResponseEntity<List<Campaign>> getCampaignsByUserId(@PathVariable Long userId) {
        Optional<List<Campaign>> campaigns = userService.getCampaignsByUserId(userId);
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all projects that a user is financing
    @GetMapping("/{id}/financing-projects")
    public ResponseEntity<List<Project>> getFinancingProjectsByUserId(@PathVariable Long userId) {
        Optional<List<Project>> projects = userService.getFinancingProjectsByUserId(userId);
        return projects.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all rewards of a user
    @GetMapping("/{id}/rewards")
    public ResponseEntity<List<Reward>> getRewardsByUserId(@PathVariable Long userId) {
        Optional<List<Reward>> rewards = userService.getRewardsByUserId(userId);
        return rewards.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all fundings of a user of donation campaigns
    @GetMapping("/{id}/donations")
    public ResponseEntity<List<Funding>> getDonationsByUserId(@PathVariable Long userId) {
        Optional<List<Funding>> donations = userService.getDonationsByUserId(userId);
        return donations.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all fundings of a user of equity campaigns
    @GetMapping("/{id}/equities")
    public ResponseEntity<List<Funding>> getEquitiesByUserId(@PathVariable Long userId) {
        Optional<List<Funding>> equities = userService.getEquitiesByUserId(userId);
        return equities.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

}
