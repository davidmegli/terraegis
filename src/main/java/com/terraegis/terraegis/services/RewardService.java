package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.Reward;
import com.terraegis.terraegis.repositories.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public Optional<Reward> getRewardById(Long id) {
        return rewardRepository.findById(id);
    }

    public Optional<List<Reward>> getAllRewards() {
        return Optional.of(rewardRepository.findAll());
    }

    public Reward createReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public Reward updateReward(Long id, Reward rewardDetails) {
        Optional<Reward> reward = rewardRepository.findById(id);
        if (reward.isPresent()) {
            Reward updatedReward = reward.get();
            updatedReward.setId(rewardDetails.getId());
            updatedReward.setCampaignId(rewardDetails.getCampaignId());
            updatedReward.setTitle(rewardDetails.getTitle());
            updatedReward.setDescription(rewardDetails.getDescription());
            updatedReward.setPrice(rewardDetails.getPrice());
            return rewardRepository.save(updatedReward);
        } else {
            return null;
        }
    }

    public boolean deleteReward(Long id) {
        Optional<Reward> reward = rewardRepository.findById(id);
        if (reward.isPresent()) {
            rewardRepository.delete(reward.get());
            return true;
        } else {
            return false;
        }
    }

}
