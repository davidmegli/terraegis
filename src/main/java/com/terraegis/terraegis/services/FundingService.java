package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.Campaign;
import com.terraegis.terraegis.models.Funding;
import com.terraegis.terraegis.repositories.CampaignRepository;
import com.terraegis.terraegis.repositories.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
public class FundingService {

    @Autowired
    private FundingRepository fundingRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    public Optional<Funding> getFundingById(Long id) {
        return fundingRepository.findById(id);
    }

    public Optional<List<Funding>> getFundingsByCampaignId(Long campaignId) {
        return fundingRepository.findFundingsByCampaignId_Id(campaignId);
    }

    public Optional<List<Funding>> getFundingsByUserId(Long userId) {
        return fundingRepository.findFundingsByUserId_Id(userId);
    }

    public Optional<List<Funding>> getFundingsByProjectId(Long projectId) {
        Optional<List<Campaign>> campaigns = campaignRepository.findCampaignsByProjectId_Id(projectId);
        List<Funding> fundings = new ArrayList<>();
        if (campaigns.isPresent()) {
            for (Campaign campaign : campaigns.get()) {
                Optional<List<Funding>> campaignFundings = fundingRepository.findFundingsByCampaignId_Id(campaign.getId());
                if (campaignFundings.isPresent()) {
                    fundings.addAll(campaignFundings.get());
                }
            }
            return Optional.of(fundings);
        } else {
            return Optional.empty();
        }
    }


    public Optional<List<Funding>> getAllFundings() {
        return Optional.of(fundingRepository.findAll());
    }

    public Funding createFunding(Funding funding) {
        return fundingRepository.save(funding);
    }

    public Funding updateFunding(Long id, Funding fundingDetails) {
        Optional<Funding> funding = fundingRepository.findById(id);
        if (funding.isPresent()) {
            Funding updatedFunding = funding.get();
            updatedFunding.setId(fundingDetails.getId());
            updatedFunding.setUserId(fundingDetails.getUserId());
            updatedFunding.setCampaignId(fundingDetails.getCampaignId());
            updatedFunding.setRewardId(fundingDetails.getRewardId());
            updatedFunding.setAmount(fundingDetails.getAmount());
            updatedFunding.setPercentage(fundingDetails.getPercentage());
            updatedFunding.setDate(fundingDetails.getDate());
            return fundingRepository.save(updatedFunding);
        } else {
            return null;
        }
    }

    public boolean deleteFunding(Long id) {
        Optional<Funding> funding = fundingRepository.findById(id);
        if (funding.isPresent()) {
            fundingRepository.delete(funding.get());
            return true;
        } else {
            return false;
        }
    }
}
