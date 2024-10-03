package com.wjl.ranker.repositories;

import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.Score;
import com.wjl.ranker.entities.embeddable.UserItemScoreKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepo extends JpaRepository<Score, UserItemScoreKey> {

//    @Query("SELECT ri.name,s.* FROM public.score s join public.ranking_item ri on s.ranking_item_id = ri.id where s.category_id = 1")
    List<Score> findScoresByCategory(Category category);
}
