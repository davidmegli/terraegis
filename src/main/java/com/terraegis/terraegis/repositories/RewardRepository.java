package com.terraegis.terraegis.repositories;

import com.terraegis.terraegis.models.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Long>{
}
