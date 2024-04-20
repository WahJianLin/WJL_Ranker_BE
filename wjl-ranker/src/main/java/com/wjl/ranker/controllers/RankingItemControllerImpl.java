package com.wjl.ranker.controllers;

import com.wjl.ranker.entities.RankingItem;
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
    public ResponseEntity<List<RankingItem>> getAllRankingItems() {
        // TODO create more specific exceptions
        try {
            return ResponseEntity.ok(rankingItemService.getAllRankingItems());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItem> getRankingItemById(Long id) {
        // TODO create more specific exceptions
        try {
            return ResponseEntity.ok(rankingItemService.getRankingItem(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItem> createRankingItem(RankingItem rankingItem) {
        // TODO create more specific exceptions
        try {
            return ResponseEntity.ok(rankingItemService.createRankingItem(rankingItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItem> updateRankingItem(RankingItem rankingItem) {
        // TODO create more specific exceptions
        try {
            return ResponseEntity.ok(rankingItemService.updateRankingItem(rankingItem));
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
