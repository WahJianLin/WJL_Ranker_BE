package com.wjl.ranker.repositories;

import com.wjl.ranker.entities.RankingItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingItemRepo extends JpaRepository<RankingItemEntity, Long> {
}
