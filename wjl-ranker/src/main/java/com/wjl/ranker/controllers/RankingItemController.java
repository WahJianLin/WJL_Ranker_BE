package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.RankingItemDTO;
import com.wjl.ranker.validations.OnUpdateValidation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface RankingItemController {
    @GetMapping()
    ResponseEntity<List<RankingItemDTO>> getAllRankingItems();

    @GetMapping("{id}")
    ResponseEntity<RankingItemDTO> getRankingItemById(@PathVariable Long id);

    @PostMapping()
    ResponseEntity<RankingItemDTO> createRankingItem(@Valid @RequestBody RankingItemDTO rankingItemDTO);

    @PutMapping()
    ResponseEntity<RankingItemDTO> updateRankingItem(@Validated(OnUpdateValidation.class) @RequestBody RankingItemDTO rankingItemDTO);

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteRankingItem(@PathVariable Long id);
}
