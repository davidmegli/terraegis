package com.terraegis.terraegis.controllers;

import com.terraegis.terraegis.models.Reward;
import com.terraegis.terraegis.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    // create a new reward
    @PostMapping("/create")
    public ResponseEntity<Reward> createReward(@RequestBody Reward reward) {
        Reward createdReward = rewardService.createReward(reward);
        return ResponseEntity.ok(createdReward);
    }

    // get all rewards
    @GetMapping("/all")
    public ResponseEntity<List<Reward>> getAllRewards() {
        Optional<List<Reward>> rewards = rewardService.getAllRewards();
        return rewards.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // get a reward by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long id) {
        Optional<Reward> reward = rewardService.getRewardById(id);
        return reward.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // update a reward
    @PutMapping("/update/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable Long id, @RequestBody Reward rewardDetails) {
        Reward updatedReward = rewardService.updateReward(id, rewardDetails);
        if (updatedReward != null) {
            return ResponseEntity.ok(updatedReward);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete a reward
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable Long id) {
        boolean isDeleted = rewardService.deleteReward(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
