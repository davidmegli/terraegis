package com.terraegis.terraegis.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sustainability_goal")
public class SustainabilityGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    public SustainabilityGoal() {
    }

    public SustainabilityGoal(Long number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
