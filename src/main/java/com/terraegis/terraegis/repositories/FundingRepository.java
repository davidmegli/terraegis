package com.terraegis.terraegis.repositories;

import com.terraegis.terraegis.models.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long>{
    Optional<List<Funding>> findFundingsByCampaignId_Id(Long campaignId);
    Optional<List<Funding>> findFundingsByUserId_Id(Long userId);
}
