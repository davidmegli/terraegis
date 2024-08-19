package com.terraegis.terraegis.repositories;

import com.terraegis.terraegis.models.SustainabilityGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface SustainabilityGoalRepository extends JpaRepository<SustainabilityGoal, Long>{
}
