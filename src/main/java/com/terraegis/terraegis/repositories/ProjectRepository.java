package com.terraegis.terraegis.repositories;

import com.terraegis.terraegis.models.Project;
import com.terraegis.terraegis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByTitle(String name);
    Optional<List<Project>> findProjectsByCreatorId(Long creatorId);
}