package com.wjl.ranker.controllers;

import com.wjl.ranker.DTO.RankingItemDTO;
import com.wjl.ranker.entities.RankingItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RankingItemController {
    @GetMapping()
    ResponseEntity<List<RankingItemDTO>> getAllRankingItems();

    @GetMapping("{id}")
    ResponseEntity<RankingItemDTO> getRankingItemById(@PathVariable Long id);

    @PostMapping()
    ResponseEntity<RankingItemDTO> createRankingItem(@RequestBody RankingItem rankingItemEntity);

    @PutMapping()
    ResponseEntity<RankingItemDTO> updateRankingItem(@RequestBody RankingItem rankingItemEntity);

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteRankingItem(@PathVariable Long id);
}
