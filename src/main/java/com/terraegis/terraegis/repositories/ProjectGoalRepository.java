package com.terraegis.terraegis.repositories;

import com.terraegis.terraegis.models.ProjectGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ProjectGoalRepository extends JpaRepository<ProjectGoal, Long>{
    Optional<List<ProjectGoal>> findProjectGoalsByProjectId_Id(Long projectId);
}