package com.wjl.ranker.services;

import com.wjl.ranker.DTO.RankingItemDTO;
import com.wjl.ranker.entities.RankingItemEntity;

import java.util.List;

public interface RankingItemService {
    List<RankingItemDTO> getAllRankingItems();

    RankingItemDTO getRankingItemById(Long id);

    RankingItemDTO createRankingItem(RankingItemEntity rankingItemEntity);

    RankingItemDTO updateRankingItem(RankingItemEntity rankingItemEntity);

    void deleteRankingItem(long id);

}
