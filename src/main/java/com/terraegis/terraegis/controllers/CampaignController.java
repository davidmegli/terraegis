package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.Campaign;
import com.terraegis.terraegis.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    // create a new campaign
    @PostMapping("/create")
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Campaign createdCampaign = campaignService.createCampaign(campaign);
        return ResponseEntity.ok(createdCampaign);
    }

    // get all campaigns
    @GetMapping("/all")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        Optional<List<Campaign>> campaigns = campaignService.getAllCampaigns();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get a campaign by ID
    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        Optional<Campaign> campaign = campaignService.getCampaignById(id);
        return campaign.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // update a campaign
    @PutMapping("/update/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long id, @RequestBody Campaign campaignDetails) {
        Campaign updatedCampaign = campaignService.updateCampaign(id, campaignDetails);
        if (updatedCampaign != null) {
            return ResponseEntity.ok(updatedCampaign);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete a campaign
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        boolean isDeleted = campaignService.deleteCampaign(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
