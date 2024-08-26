package com.terraegis.terraegis.models;
import  jakarta.persistence.*;
import java.util.Date;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "funding")
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaignId;

    //if the campaign is a reward campaign, the amount will be the price of the reward
    //if the campaign is a donation campaign, the amount will be the donation amount
    //if the campaign is an equity campaign, the amount will be the amount of equity bought and the percentage will be the percentage of equity bought
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Size(min = 0, max = 100)
    @Column(name = "percentage", nullable = false)
    private Long percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id", nullable = true)
    private Reward rewardId;

    @Column(name = "date", nullable = false)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Campaign getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Campaign campaignId) {
        this.campaignId = campaignId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public Reward getRewardId() {
        return rewardId;
    }

    public void setRewardId(Reward rewardId) {
        this.rewardId = rewardId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
