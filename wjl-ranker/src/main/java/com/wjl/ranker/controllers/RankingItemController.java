package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.RankingItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RankingItemController {
    @GetMapping()
    ResponseEntity<List<RankingItemDTO>> getAllRankingItems();

    @GetMapping("{id}")
    ResponseEntity<RankingItemDTO> getRankingItemById(@PathVariable Long id);

    @PostMapping()
    ResponseEntity<RankingItemDTO> createRankingItem(@RequestBody RankingItemDTO rankingItemDTO);

    @PutMapping()
    ResponseEntity<RankingItemDTO> updateRankingItem(@RequestBody RankingItemDTO rankingItemDTO);

    @DeleteMapping("{id}")
    ResponseEntity deleteRankingItem(@PathVariable Long id);
}
