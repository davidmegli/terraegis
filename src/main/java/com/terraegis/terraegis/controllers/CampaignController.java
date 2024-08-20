package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.Campaign;
import com.terraegis.terraegis.services.CampaignService;
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

    // get campaigns by project ID
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Campaign>> getCampaignByProjectId(@PathVariable Long projectId) {
        Optional<List<Campaign>> campaign = campaignService.getCampaignsByProjectId(projectId);
        return campaign.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get active campaigns
    @GetMapping("/active")
    public ResponseEntity<List<Campaign>> getActiveCampaigns() {
        Optional<List<Campaign>> campaigns = campaignService.getActiveCampaigns();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get ended campaigns
    @GetMapping("/ended")
    public ResponseEntity<List<Campaign>> getEndedCampaigns() {
        Optional<List<Campaign>> campaigns = campaignService.getEndedCampaigns();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get active campaigns by project ID
    @GetMapping("/active/project/{projectId}")
    public ResponseEntity<List<Campaign>> getActiveCampaignsByProjectId(@PathVariable Long projectId) {
        Optional<List<Campaign>> campaigns = campaignService.getActiveCampaignsByProjectId(projectId);
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get ended campaigns by project ID
    @GetMapping("/ended/project/{projectId}")
    public ResponseEntity<List<Campaign>> getEndedCampaignsByProjectId(@PathVariable Long projectId) {
        Optional<List<Campaign>> campaigns = campaignService.getEndedCampaignsByProjectId(projectId);
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get campaigns with goal greater than or equal to the given amount
    @GetMapping("/goal/{amount}")
    public ResponseEntity<List<Campaign>> getCampaignsWithGoalGreaterThan(@PathVariable Long amount) {
        Optional<List<Campaign>> campaigns = campaignService.getCampaignsWithGoalGreaterThan(amount);
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get campaigns with goal lesser than or equal to the given amount
    @GetMapping("/goal/lesser-than/{amount}")
    public ResponseEntity<List<Campaign>> getCampaignsWithGoalLesserThan(@PathVariable Long amount) {
        Optional<List<Campaign>> campaigns = campaignService.getCampaignsWithGoalLesserThan(amount);
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get campaigns with funding goal reached
    @GetMapping("/funding-reached")
    public ResponseEntity<List<Campaign>> getCampaignsWithFundingGoalReached() {
        Optional<List<Campaign>> campaigns = campaignService.getCampaignsWithFundingGoalReached();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get campaigns with funding goal not reached
    @GetMapping("/funding-not-reached")
    public ResponseEntity<List<Campaign>> getCampaignsWithFundingGoalNotReached() {
        Optional<List<Campaign>> campaigns = campaignService.getCampaignsWithFundingGoalNotReached();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get active campaigns with funding goal reached
    @GetMapping("/active/funding-reached")
    public ResponseEntity<List<Campaign>> getActiveCampaignsWithFundingGoalReached() {
        Optional<List<Campaign>> campaigns = campaignService.getActiveCampaignsWithFundingGoalReached();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get active campaigns with funding goal not reached
    @GetMapping("/active/funding-not-reached")
    public ResponseEntity<List<Campaign>> getActiveCampaignsWithFundingGoalNotReached() {
        Optional<List<Campaign>> campaigns = campaignService.getActiveCampaignsWithFundingGoalNotReached();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get ended campaigns with funding goal reached
    @GetMapping("/ended/funding-reached")
    public ResponseEntity<List<Campaign>> getEndedCampaignsWithFundingGoalReached() {
        Optional<List<Campaign>> campaigns = campaignService.getEndedCampaignsWithFundingGoalReached();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get ended campaigns with funding goal not reached
    @GetMapping("/ended/funding-not-reached")
    public ResponseEntity<List<Campaign>> getEndedCampaignsWithFundingGoalNotReached() {
        Optional<List<Campaign>> campaigns = campaignService.getEndedCampaignsWithFundingGoalNotReached();
        return campaigns.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
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
