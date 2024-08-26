package com.terraegis.terraegis.datainitializer;
import com.terraegis.terraegis.models.SustainabilityGoal;
import com.terraegis.terraegis.services.SustainabilityGoalService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private SustainabilityGoalService goalService;

    @PostConstruct
    public void init() {
        List<SustainabilityGoal> goals = new ArrayList<>();
        // Adding the 17 UN Sustainable Development Goals
        goals.add(new SustainabilityGoal(1L, "No Poverty", "End poverty in all its forms everywhere"));
        goals.add(new SustainabilityGoal(2L, "Zero Hunger", "End hunger, achieve food security and improved nutrition and promote sustainable agriculture"));
        goals.add(new SustainabilityGoal(3L, "Good Health and Well-being", "Ensure healthy lives and promote well-being for all at all ages"));
        goals.add(new SustainabilityGoal(4L, "Quality Education", "Ensure inclusive and equitable quality education and promote lifelong learning opportunities for all"));
        goals.add(new SustainabilityGoal(5L, "Gender Equality", "Achieve gender equality and empower all women and girls"));
        goals.add(new SustainabilityGoal(6L, "Clean Water and Sanitation", "Ensure availability and sustainable management of water and sanitation for all"));
        goals.add(new SustainabilityGoal(7L, "Affordable and Clean Energy", "Ensure access to affordable, reliable, sustainable and modern energy for all"));
        goals.add(new SustainabilityGoal(8L, "Decent Work and Economic Growth", "Promote sustained, inclusive and sustainable economic growth, full and productive employment and decent work for all"));
        goals.add(new SustainabilityGoal(9L, "Industry, Innovation, and Infrastructure", "Build resilient infrastructure, promote inclusive and sustainable industrialization and foster innovation"));
        goals.add(new SustainabilityGoal(10L, "Reduced Inequality", "Reduce inequality within and among countries"));
        goals.add(new SustainabilityGoal(11L, "Sustainable Cities and Communities", "Make cities and human settlements inclusive, safe, resilient and sustainable"));
        goals.add(new SustainabilityGoal(12L, "Responsible Consumption and Production", "Ensure sustainable consumption and production patterns"));
        goals.add(new SustainabilityGoal(13L, "Climate Action", "Take urgent action to combat climate change and its impacts"));
        goals.add(new SustainabilityGoal(14L, "Life Below Water", "Conserve and sustainably use the oceans, seas and marine resources for sustainable development"));
        goals.add(new SustainabilityGoal(15L, "Life on Land", "Protect, restore and promote sustainable use of terrestrial ecosystems, sustainably manage forests, combat desertification, and halt and reverse land degradation and halt biodiversity loss"));
        goals.add(new SustainabilityGoal(16L, "Peace, Justice, and Strong Institutions", "Promote peaceful and inclusive societies for sustainable development, provide access to justice for all and build effective, accountable and inclusive institutions at all levels"));
        goals.add(new SustainabilityGoal(17L, "Partnerships for the Goals", "Strengthen the means of implementation and revitalize the Global Partnership for Sustainable Development"));

        goalService.saveGoalsIfNotExists(goals);
    }
}
