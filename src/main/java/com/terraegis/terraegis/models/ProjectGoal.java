package com.terraegis.terraegis.models;
import jakarta.persistence.*;

@Entity
@Table(name = "project_goal")
public class ProjectGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Column(name = "sustainability_goal_id", nullable = false)
    private SustainabilityGoal sustainabilityGoalId;

    @Column(name = "description", nullable = false)
    private String description;

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

    public SustainabilityGoal getSustainabilityGoalId() {
        return sustainabilityGoalId;
    }

    public void setSustainabilityGoalId(SustainabilityGoal sustainabilityGoalId) {
        this.sustainabilityGoalId = sustainabilityGoalId;
    }
}
