package com.wjl.ranker.repositories;

import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.RankingItem;
import com.wjl.ranker.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingItemRepo extends JpaRepository<RankingItem, Long> {

    List<RankingItem> findRankingItemsByCategory(Category category);
}
