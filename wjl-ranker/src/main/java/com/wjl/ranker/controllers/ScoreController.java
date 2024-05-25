package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.ScoreDTO;
import com.wjl.ranker.dto.UserItemScoreKeyDTO;
import com.wjl.ranker.entities.Score;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface ScoreController {
    // get all scores
    @GetMapping()
    ResponseEntity<List<ScoreDTO>> getAllScores();

    @GetMapping("category/{categoryId}")
    ResponseEntity<List<ScoreDTO>> getScoresByCategory(@PathVariable Long categoryId);
    // put logic on a bunch of scores

    @PutMapping("category/{categoryId}")
    ResponseEntity<List<ScoreDTO>> updateScoresByCategoryAndUser(@RequestBody List<@Valid ScoreDTO> scoreDtoList, @PathVariable Long categoryId);
    // delete logic for a bunch of scores
    @DeleteMapping()
    ResponseEntity deleteScoresByCategoryAndUser(@RequestBody List<@Valid UserItemScoreKeyDTO> UserItemScoreKeyDTOList);

}
