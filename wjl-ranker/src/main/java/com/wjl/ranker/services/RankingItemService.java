package com.wjl.ranker.services;

import com.wjl.ranker.entities.RankingItem;

import java.util.List;

public interface RankingItemService {
    List<RankingItem> getAllRankingItems();

    RankingItem getRankingItemById(Long id);

    RankingItem createRankingItem(RankingItem rankingItemEntity);

    RankingItem updateRankingItem(RankingItem rankingItemEntity);

    void deleteRankingItem(long id);

}
