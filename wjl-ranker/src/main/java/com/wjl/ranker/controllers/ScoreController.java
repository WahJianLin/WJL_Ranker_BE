package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.ScoreDTO;
import com.wjl.ranker.dto.UserItemScoreKeyDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface ScoreController {
    @GetMapping()
    ResponseEntity<List<ScoreDTO>> getAllScores();

    @GetMapping("category/{categoryId}")
    ResponseEntity<List<ScoreDTO>> getScoresByCategory(@PathVariable Long categoryId);

    @PutMapping("category/{categoryId}")
    ResponseEntity<List<ScoreDTO>> updateScoresByCategoryAndUser(@RequestBody List<@Valid ScoreDTO> scoreDtoList, @PathVariable Long categoryId);

    @DeleteMapping()
    ResponseEntity<Void> deleteScoresByCategoryAndUser(@RequestBody List<@Valid UserItemScoreKeyDTO> userItemScoreKeyDTOList);

}
