package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.Campaign;
import com.terraegis.terraegis.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

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
        return campaignRepository.findCampaignsByProjectId(projectId);
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
            updatedCampaign.setName(campaignDetails.getName());
            updatedCampaign.setDescription(campaignDetails.getDescription());
            updatedCampaign.setGoal(campaignDetails.getGoal());
            updatedCampaign.setProject(campaignDetails.getProject());
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
        return campaignRepository.findCampaignsByCreatorId(creatorId);
    }

    public Optional<List<Campaign>> getCampaignsByProjectId(Long projectId) {
        return campaignRepository.findCampaignsByProjectId(projectId);
    }
}
