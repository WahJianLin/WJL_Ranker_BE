package com.wjl.ranker.services;

import com.wjl.ranker.entities.RankingItem;

import java.util.List;

public interface RankingItemService {
    List<RankingItem> getAll();

    RankingItem getByID(Long id);

    RankingItem create(RankingItem rankingItemEntity);

    RankingItem update(RankingItem rankingItemEntity);

    void deleteById(long id);

}
