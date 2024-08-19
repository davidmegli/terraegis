package com.terraegis.terraegis.models;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "campaign")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "funding_goal", nullable = false)
    private Long fundingGoal;

    @Column(name = "funding_raised", nullable = false)
    private Long fundingRaised;

    @Column(name ="minimum_amount", nullable = false)
    private Long minimumAmount;

    @Column(name = "type", nullable = false)
    private String type; // Equity, Donation, Reward

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProjectId() {
        return projectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getFundingGoal() {
        return fundingGoal;
    }

    public void setFundingGoal(Long fundingGoal) {
        this.fundingGoal = fundingGoal;
    }

    public Long getFundingRaised() {
        return fundingRaised;
    }

    public void setFundingRaised(Long fundingRaised) {
        this.fundingRaised = fundingRaised;
    }

    public Long getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(Long minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
