package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.Campaign;
import com.terraegis.terraegis.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.Date;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }

    public Optional<Campaign> getCampaignByName(String name) {
        return campaignRepository.findByName(name);
    }

    public Optional<List<Campaign>> getCampaignsByProjectId(Long projectId) {
        return campaignRepository.findCampaignsByProjectId_Id(projectId);
    }

    public Optional<List<Campaign>> getAllCampaigns() {
        return Optional.of(campaignRepository.findAll());
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaign(Long id, Campaign campaignDetails) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isPresent()) {
            Campaign updatedCampaign = campaign.get();
            updatedCampaign.setProject(campaignDetails.getProject());
            updatedCampaign.setName(campaignDetails.getName());
            updatedCampaign.setDescription(campaignDetails.getDescription());
            updatedCampaign.setStartDate(campaignDetails.getStartDate());
            updatedCampaign.setEndDate(campaignDetails.getEndDate());
            updatedCampaign.setFundingGoal(campaignDetails.getFundingGoal());
            updatedCampaign.setFundingRaised(campaignDetails.getFundingRaised());
            updatedCampaign.setMinimumAmount(campaignDetails.getMinimumAmount());
            updatedCampaign.setType(campaignDetails.getType());
            return campaignRepository.save(updatedCampaign);
        } else {
            return null;
        }
    }

    public boolean deleteCampaign(Long id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isPresent()) {
            campaignRepository.delete(campaign.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<List<Campaign>> getCampaignsByCreatorId(Long creatorId) {
        return campaignRepository.findCampaignsByProjectId_Id(creatorId);
    }

    public Optional<List<Campaign>> getActiveCampaigns() {
        Optional<List<Campaign>> campaigns = getAllCampaigns();
        List<Campaign> activeCampaigns = new ArrayList<Campaign>();
        Date currentDate = new Date();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getStartDate().before(currentDate) && campaign.getEndDate().after(currentDate)) {
                activeCampaigns.add(campaign);
            }
        }
        return Optional.of(activeCampaigns);
    }

    public Optional<List<Campaign>> getEndedCampaigns() {
        Optional<List<Campaign>> campaigns = getAllCampaigns();
        List<Campaign> endedCampaigns = new ArrayList<Campaign>();
        Date currentDate = new Date();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getEndDate().before(currentDate)) {
                endedCampaigns.add(campaign);
            }
        }
        return Optional.of(endedCampaigns);
    }

    public Optional<List<Campaign>> getActiveCampaignsByProjectId(Long projectId) {
        Optional<List<Campaign>> campaigns = getCampaignsByProjectId(projectId);
        List<Campaign> activeCampaigns = new ArrayList<Campaign>();
        Date currentDate = new Date();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getStartDate().before(currentDate) && campaign.getEndDate().after(currentDate)) {
                activeCampaigns.add(campaign);
            }
        }
        return Optional.of(activeCampaigns);
    }

    public Optional<List<Campaign>> getEndedCampaignsByProjectId(Long projectId) {
        Optional<List<Campaign>> campaigns = getCampaignsByProjectId(projectId);
        List<Campaign> endedCampaigns = new ArrayList<Campaign>();
        Date currentDate = new Date();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getEndDate().before(currentDate)) {
                endedCampaigns.add(campaign);
            }
        }
        return Optional.of(endedCampaigns);
    }

    public Optional<List<Campaign>> getCampaignsWithGoalGreaterThan(Long amount) {
        Optional<List<Campaign>> campaigns = getAllCampaigns();
        List<Campaign> campaignsWithGoalGreaterThan = new ArrayList<Campaign>();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getFundingGoal() >= amount) {
                campaignsWithGoalGreaterThan.add(campaign);
            }
        }
        return Optional.of(campaignsWithGoalGreaterThan);
    }

    public Optional<List<Campaign>> getCampaignsWithGoalLesserThan(Long amount) {
        Optional<List<Campaign>> campaigns = getAllCampaigns();
        List<Campaign> campaignsWithGoalLessThan = new ArrayList<Campaign>();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getFundingGoal() <= amount) {
                campaignsWithGoalLessThan.add(campaign);
            }
        }
        return Optional.of(campaignsWithGoalLessThan);
    }

    public Optional<List<Campaign>> getCampaignsWithFundingGoalReached() {
        Optional<List<Campaign>> campaigns = getAllCampaigns();
        List<Campaign> campaignsWithFundingGoalReached = new ArrayList<Campaign>();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getFundingRaised() >= campaign.getFundingGoal()) {
                campaignsWithFundingGoalReached.add(campaign);
            }
        }
        return Optional.of(campaignsWithFundingGoalReached);
    }

    public Optional<List<Campaign>> getCampaignsWithFundingGoalNotReached() {
        Optional<List<Campaign>> campaigns = getAllCampaigns();
        List<Campaign> campaignsWithFundingGoalNotReached = new ArrayList<Campaign>();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getFundingRaised() < campaign.getFundingGoal()) {
                campaignsWithFundingGoalNotReached.add(campaign);
            }
        }
        return Optional.of(campaignsWithFundingGoalNotReached);
    }

    public Optional<List<Campaign>> getActiveCampaignsWithFundingGoalReached() {
        Optional<List<Campaign>> campaigns = getActiveCampaigns();
        List<Campaign> activeCampaignsWithFundGoalReached = new ArrayList<Campaign>();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getFundingRaised() >= campaign.getFundingGoal()) {
                activeCampaignsWithFundGoalReached.add(campaign);
            }
        }
        return Optional.of(activeCampaignsWithFundGoalReached);
    }

    public Optional<List<Campaign>> getActiveCampaignsWithFundingGoalNotReached() {
        Optional<List<Campaign>> campaigns = getActiveCampaigns();
        List<Campaign> activeCampaignsWithFundGoalNotReached = new ArrayList<Campaign>();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getFundingRaised() < campaign.getFundingGoal()) {
                activeCampaignsWithFundGoalNotReached.add(campaign);
            }
        }
        return Optional.of(activeCampaignsWithFundGoalNotReached);
    }

    public Optional<List<Campaign>> getEndedCampaignsWithFundingGoalReached() {
        Optional<List<Campaign>> campaigns = getEndedCampaigns();
        List<Campaign> endedCampaignsWithFundGoalReached = new ArrayList<Campaign>();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getFundingRaised() >= campaign.getFundingGoal()) {
                endedCampaignsWithFundGoalReached.add(campaign);
            }
        }
        return Optional.of(endedCampaignsWithFundGoalReached);
    }

    public Optional<List<Campaign>> getEndedCampaignsWithFundingGoalNotReached() {
        Optional<List<Campaign>> campaigns = getEndedCampaigns();
        List<Campaign> endedCampaignsWithFundGoalNotReached = new ArrayList<Campaign>();
        for (Campaign campaign : campaigns.get()) {
            if (campaign.getFundingRaised() < campaign.getFundingGoal()) {
                endedCampaignsWithFundGoalNotReached.add(campaign);
            }
        }
        return Optional.of(endedCampaignsWithFundGoalNotReached);
    }
}
