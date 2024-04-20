package com.wjl.ranker.controllers;

import com.wjl.ranker.DTO.RankingItemDTO;
import com.wjl.ranker.entities.RankingItemEntity;
import com.wjl.ranker.services.RankingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/ranking-items")
public class RankingItemControllerImpl implements RankingItemController {

    private final RankingItemService rankingItemService;

    @Autowired
    public RankingItemControllerImpl(RankingItemService rankingItemService) {
        this.rankingItemService = rankingItemService;
    }

    @Override
    public ResponseEntity<List<RankingItemDTO>> getAllRankingItems() {
        // TODO create more specific exceptions
        try {
            return ResponseEntity.ok(rankingItemService.getAllRankingItems());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItemDTO> getRankingItemById(Long id) {
        // TODO create more specific exceptions
        try {
            return ResponseEntity.ok(rankingItemService.getRankingItemById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItemDTO> createRankingItem(RankingItemEntity rankingItemEntity) {
        // TODO create more specific exceptions
        try {
            return ResponseEntity.ok(rankingItemService.createRankingItem(rankingItemEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItemDTO> updateRankingItem(RankingItemEntity rankingItemEntity) {
        // TODO create more specific exceptions
        try {
            return ResponseEntity.ok(rankingItemService.updateRankingItem(rankingItemEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity deleteRankingItem(Long id) {
        try {
            rankingItemService.deleteRankingItem(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
