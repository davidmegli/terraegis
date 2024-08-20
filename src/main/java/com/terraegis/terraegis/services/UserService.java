package com.terraegis.terraegis.services;

import com.terraegis.terraegis.models.*;
import com.terraegis.terraegis.repositories.CampaignRepository;
import com.terraegis.terraegis.repositories.FundingRepository;
import com.terraegis.terraegis.repositories.ProjectRepository;
import com.terraegis.terraegis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private FundingRepository fundingRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    public Optional<List<Project>> findProjectsByCreatorId(Long creatorId) {
        return projectRepository.findProjectsByCreatorId(creatorId);
    }

    public Optional<List<User>> getAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPasswordHash().equals(password)) {
            return user.get();
        } else {
            return null;
        }
    }

    public User updateUser(Long id, User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setName(userDetails.getName());
            updatedUser.setEmail(userDetails.getEmail());
            updatedUser.setPasswordHash(userDetails.getPasswordHash());
            updatedUser.setRole(userDetails.getRole());
            return userRepository.save(updatedUser);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<List<Funding>> getFundingsByUserId(Long userId) {
        return fundingRepository.findFundingsByUserId(userId);
    }

    public Optional<List<Campaign>> getCampaignsByUserId(Long userId) {
        Optional<List<Funding>> fundingsByUser = fundingRepository.findFundingsByUserId(userId);
        List<Campaign> campaigns = new ArrayList<>();
        fundingsByUser.ifPresent(fundings -> {
            fundings.forEach(funding -> {
                if (funding.getCampaignId() != null) {
                    campaigns.add(funding.getCampaignId());
                }
            });
        });
        return Optional.of(campaigns);
    }

    public Optional<List<Project>> getFinancingProjectsByUserId(Long userId) {
        Optional<List<Funding>> fundingsByUser = fundingRepository.findFundingsByUserId(userId);
        List<Project> projects = new ArrayList<>();
        // get all campaigns ids in fundingsByUser
        List<Long> campaignIds = new ArrayList<>();
        fundingsByUser.ifPresent(fundings -> {
            fundings.forEach(funding -> {
                if (funding.getCampaignId() != null) {
                    campaignIds.add(funding.getCampaignId().getId());
                }
            });
        });
        List<Campaign> campaigns = new ArrayList<>();
        // get all campaigns by campaignIds
        campaignIds.forEach(campaignId -> {
            Optional<Campaign> campaign = campaignRepository.findById(campaignId);
            campaign.ifPresent(campaigns::add);
        });
        // get all projects ids in campaigns
        List<Long> projectIds = new ArrayList<>();
        campaigns.forEach(campaign -> {
            projectIds.add(campaign.getProject().getId());
        });
        // get all projects by projectIds
        projectIds.forEach(projectId -> {
            Optional<Project> project = projectRepository.findById(projectId);
            project.ifPresent(projects::add);
        });
        return Optional.of(projects);
    }

    public Optional<List<Funding>> getEquitiesByUserId(Long userId) {
        Optional<List<Funding>> fundingsByUser = fundingRepository.findFundingsByUserId(userId);
        List<Funding> equities = new ArrayList<>();
        fundingsByUser.ifPresent(fundings -> {
            fundings.forEach(funding -> {
                if (funding.getRewardId() == null && funding.getPercentage() > 0) {
                    equities.add(funding);
                }
            });
        });
        return Optional.of(equities);
    }

    public Optional<List<Funding>> getDonationsByUserId(Long userId) {
        Optional<List<Funding>> fundingsByUser = fundingRepository.findFundingsByUserId(userId);
        List<Funding> donations = new ArrayList<>();
        fundingsByUser.ifPresent(fundings -> {
            fundings.forEach(funding -> {
                if (funding.getRewardId() == null && funding.getPercentage() == 0){
                    donations.add(funding);
                }
            });
        });
        return Optional.of(donations);
    }

    public Optional<List<Reward>> getRewardsByUserId(Long userId) {
        Optional<List<Funding>> fundingsByUser = fundingRepository.findFundingsByUserId(userId);
        List<Reward> rewards = new ArrayList<>();
        fundingsByUser.ifPresent(fundings -> {
            fundings.forEach(funding -> {
                if (funding.getRewardId() != null) {
                    rewards.add(funding.getRewardId());
                }
            });
        });
        return Optional.of(rewards);
    }

}
