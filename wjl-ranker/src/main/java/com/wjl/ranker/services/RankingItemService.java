package com.wjl.ranker.services;

import com.wjl.ranker.DTO.RankingItemDTO;
import com.wjl.ranker.entities.RankingItem;

import java.util.List;

public interface RankingItemService {
    List<RankingItemDTO> getAllRankingItems();

    RankingItemDTO getRankingItemById(Long id);

    RankingItemDTO createRankingItem(RankingItem rankingItemEntity);

    RankingItemDTO updateRankingItem(RankingItem rankingItemEntity);

    void deleteRankingItem(long id);

}
