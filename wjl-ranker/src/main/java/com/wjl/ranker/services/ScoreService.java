package com.wjl.ranker.services;

import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.Score;
import com.wjl.ranker.entities.embeddable.UserItemScoreKey;

import java.util.List;

public interface ScoreService {
    List<Score> getAll();

    List<Score> getAllByCategory(Category categoryEntity);

    List<Score> updateBulk(List<Score> scoreList);

    void deleteBulk(List<UserItemScoreKey> compositeIdList);
}
