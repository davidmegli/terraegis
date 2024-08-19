package com.terraegis.terraegis.repositories;

import com.terraegis.terraegis.models.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long>{
}
