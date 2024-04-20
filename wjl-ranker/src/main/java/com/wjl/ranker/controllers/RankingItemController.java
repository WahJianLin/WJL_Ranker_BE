package com.wjl.ranker.controllers;

import com.wjl.ranker.entities.RankingItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RankingItemController {
    @GetMapping()
    ResponseEntity<List<RankingItem>> getAllRankingItems();

    @GetMapping("{id}")
    ResponseEntity<RankingItem> getRankingItemById(@PathVariable Long id);

    @PostMapping()
    ResponseEntity<RankingItem> createRankingItem(@RequestBody RankingItem rankingItem);

    @PutMapping()
    ResponseEntity<RankingItem> updateRankingItem(@RequestBody RankingItem rankingItem);

    @DeleteMapping("{id}")
    ResponseEntity deleteRankingItem(@PathVariable Long id);
}
