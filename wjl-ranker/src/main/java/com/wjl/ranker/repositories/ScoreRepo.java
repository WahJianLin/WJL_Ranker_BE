package com.wjl.ranker.repositories;

import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.Score;
import com.wjl.ranker.entities.embeddable.UserItemScoreKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepo extends JpaRepository<Score, UserItemScoreKey> {

    List<Score> findScoresByCategory(Category category);
}
