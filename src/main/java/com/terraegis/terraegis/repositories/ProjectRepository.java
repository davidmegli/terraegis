package com.terraegis.terraegis.repositories;

import com.terraegis.terraegis.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByTitle(String name);
    Optional<List<Project>> findProjectsByCreatorId_Id(Long creatorId);
    Optional<List<Project>> findProjectsByCategoryId_Id(Long categoryId);
}