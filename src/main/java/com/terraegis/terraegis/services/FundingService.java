package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.Funding;
import com.terraegis.terraegis.repositories.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class FundingService {

    @Autowired
    private FundingRepository fundingRepository;

    public Optional<Funding> getFundingById(Long id) {
        return fundingRepository.findById(id);
    }

    public Optional<List<Funding>> getFundingsByCampaignId(Long campaignId) {
        return fundingRepository.findFundingsByCampaignId(campaignId);
    }

    public Optional<List<Funding>> getFundingsByUserId(Long userId) {
        return fundingRepository.findFundingsByUserId(userId);
    }

    public Optional<List<Funding>> getFundingsByProjectId(Long projectId) {
        return fundingRepository.findFundingsByProjectId(projectId);
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
