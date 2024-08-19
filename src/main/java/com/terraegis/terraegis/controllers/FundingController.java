package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.Funding;
import com.terraegis.terraegis.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fundings")
public class FundingController {

    @Autowired
    private FundingService fundingService;

    // create a new funding
    @PostMapping("/create")
    public ResponseEntity<Funding> createFunding(@RequestBody Funding funding) {
        Funding createdFunding = fundingService.createFunding(funding);
        return ResponseEntity.ok(createdFunding);
    }

    // get all fundings
    @GetMapping("/all")
    public ResponseEntity<List<Funding>> getAllFundings() {
        Optional<List<Funding>> fundings = fundingService.getAllFundings();
        return fundings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get a funding by ID
    @GetMapping("/{id}")
    public ResponseEntity<Funding> getFundingById(@PathVariable Long id) {
        Optional<Funding> funding = fundingService.getFundingById(id);
        return funding.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // update a funding
    @PutMapping("/update/{id}")
    public ResponseEntity<Funding> updateFunding(@PathVariable Long id, @RequestBody Funding fundingDetails) {
        Funding updatedFunding = fundingService.updateFunding(id, fundingDetails);
        if (updatedFunding != null) {
            return ResponseEntity.ok(updatedFunding);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete a funding
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFunding(@PathVariable Long id) {
        boolean isDeleted = fundingService.deleteFunding(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // get all fundings by project ID
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Funding>> getFundingsByProjectId(@PathVariable Long projectId) {
        Optional<List<Funding>> fundings = fundingService.getFundingsByProjectId(projectId);
        return fundings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get all fundings by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Funding>> getFundingsByUserId(@PathVariable Long userId) {
        Optional<List<Funding>> fundings = fundingService.getFundingsByUserId(userId);
        return fundings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
