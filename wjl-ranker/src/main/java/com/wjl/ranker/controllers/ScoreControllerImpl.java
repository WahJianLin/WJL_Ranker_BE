package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.ScoreDTO;
import com.wjl.ranker.dto.UserItemScoreKeyDTO;
import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.Score;
import com.wjl.ranker.entities.embeddable.UserItemScoreKey;
import com.wjl.ranker.services.CategoryService;
import com.wjl.ranker.services.RankingItemService;
import com.wjl.ranker.services.ScoreService;
import com.wjl.ranker.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/score")
public class ScoreControllerImpl implements ScoreController{

    private final ScoreService scoreService;
    private final UserAccountService userAccountService;
    private final RankingItemService rankingItemService;

    private final CategoryService categoryService;

    @Autowired
    public ScoreControllerImpl(ScoreService scoreService, UserAccountService userAccountService, RankingItemService rankingItemService, CategoryService categoryService) {
        this.scoreService = scoreService;
        this.userAccountService = userAccountService;
        this.rankingItemService = rankingItemService;
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<List<ScoreDTO>> getAllScores() {
        List<Score> response = scoreService.getAll();
        return ResponseEntity.ok().body(response.stream().map(this::toDTO).toList());
    }

    @Override
    public ResponseEntity<List<ScoreDTO>> getScoresByCategory(Long categoryId) {
        List<Score> response = scoreService.getAllByCategory(categoryService.getById(categoryId));
        return ResponseEntity.ok().body(response.stream().map(this::toDTO).toList());
    }

    @Override
    public ResponseEntity<List<ScoreDTO>> updateScoresByCategoryAndUser(List<ScoreDTO> scoreDtoList, Long categoryId) {
        Category category = categoryService.getById(categoryId);
        List<Score> scores = scoreDtoList.stream().map((scoreDTO)-> toEntity(scoreDTO, category)).toList();
        List<Score> response = scoreService.updateBulk(scores);
        return ResponseEntity.ok().body(response.stream().map(this::toDTO).toList());
    }

    @Override
    public ResponseEntity deleteScoresByCategoryAndUser(List<UserItemScoreKeyDTO> UserItemScoreKeyDTOList) {
        List<UserItemScoreKey> compositeIds = UserItemScoreKeyDTOList.stream().map((dto)-> new UserItemScoreKey(dto.getUserAccountId(), dto.getRankingItemId())).toList();
        scoreService.deleteBulk(compositeIds);
        return ResponseEntity.noContent().build();
    }

    private ScoreDTO toDTO(Score score) {
        ScoreDTO dto = new ScoreDTO();
        dto.setUserAccountId(score.getUserAccount().getId());
        dto.setRankingItemId(score.getRankingItem().getId());
        dto.setCategoryId(score.getCategory().getId());
        dto.setScoreValue(score.getScoreValue());
        return dto;
    }

    private Score toEntity(ScoreDTO scoreDTO, Category category) {
        UserItemScoreKey id = new UserItemScoreKey(scoreDTO.getUserAccountId(), scoreDTO.getRankingItemId());

        Score score = new Score();
        score.setScoreValue(scoreDTO.getScoreValue());
        score.setUserAccount(
                userAccountService.getById(scoreDTO.getUserAccountId())
        );
        score.setRankingItem(
                rankingItemService.getByID(scoreDTO.getRankingItemId())
        );
        score.setCompositeId(id);
        if (Objects.nonNull(category)) {
            score.setCategory(category);
        }
        return score;
    }
}
