package com.terraegis.terraegis.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sustainability_goal_id", nullable = false)
    private SustainabilityGoal sustainabilityGoal;

    @Column(name = "funding_goal", nullable = false)
    private BigDecimal fundingGoal;

    @Column(name = "current_funds", nullable = false)
    private BigDecimal currentFunds = BigDecimal.ZERO;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    //TODO: define the goals in a quantitative way

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public SustainabilityGoal getSustainabilityGoal() {
        return sustainabilityGoal;
    }

    public void setSustainabilityGoal(SustainabilityGoal sustainabilityGoal) {
        this.sustainabilityGoal = sustainabilityGoal;
    }

    public BigDecimal getFundingGoal() {
        return fundingGoal;
    }

    public void setFundingGoal(BigDecimal fundingGoal) {
        this.fundingGoal = fundingGoal;
    }

    public BigDecimal getCurrentFunds() {
        return currentFunds;
    }

    public void setCurrentFunds(BigDecimal currentFunds) {
        this.currentFunds = currentFunds;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setStatus(Boolean isActive) {
        this.isActive = isActive;
    }

}
