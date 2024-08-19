package com.terraegis.terraegis.repositories;

import com.terraegis.terraegis.models.Campaign;
import com.terraegis.terraegis.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Optional<Campaign> findByName(String name);
    Optional<List<Campaign>> findCampaignsByProjectId(Long projectId);
}