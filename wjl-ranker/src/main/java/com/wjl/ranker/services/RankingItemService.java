package com.wjl.ranker.services;

import com.wjl.ranker.entities.RankingItem;

import java.util.List;

public interface RankingItemService {
    List<RankingItem> getAllRankingItems();
    RankingItem getRankingItem(Long id);
    RankingItem createRankingItem(RankingItem rankingItem);
    RankingItem updateRankingItem(RankingItem rankingItem);
    void deleteRankingItem(long id);
}
