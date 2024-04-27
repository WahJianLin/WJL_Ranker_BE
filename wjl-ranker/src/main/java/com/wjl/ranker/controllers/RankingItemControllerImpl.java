package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.RankingItemDTO;
import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.RankingItem;
import com.wjl.ranker.services.CategoryService;
import com.wjl.ranker.services.RankingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/ranking-item")
public class RankingItemControllerImpl implements RankingItemController {

    private final RankingItemService rankingItemService;

    private final CategoryService categoryService;

    @Autowired
    public RankingItemControllerImpl(RankingItemService rankingItemService, CategoryService categoryService) {
        this.rankingItemService = rankingItemService;
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<List<RankingItemDTO>> getAllRankingItems() {
        // TODO create more specific exceptions
        try {
            List<RankingItem> response = rankingItemService.getAllRankingItems();
            return ResponseEntity.ok(response.stream().map(this::toDTO).toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItemDTO> getRankingItemById(Long id) {
        // TODO create more specific exceptions
        try {
            RankingItem response = rankingItemService.getRankingItemById(id);
            return ResponseEntity.ok(toDTO(response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItemDTO> createRankingItem(RankingItemDTO rankingItemDTO) {
        // TODO create more specific exceptions
        try {
            RankingItem response = rankingItemService.createRankingItem(toEntity(rankingItemDTO));
            return ResponseEntity.ok(toDTO(response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<RankingItemDTO> updateRankingItem(RankingItemDTO rankingItemDTO) {
        // TODO create more specific exceptions
        try {
            // make this work with entity instead
            RankingItem response = rankingItemService.updateRankingItem(toEntity(rankingItemDTO));
            return ResponseEntity.ok(toDTO(response));
        } catch (Exception e) {
            // return body later
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

    private RankingItemDTO toDTO(RankingItem rankingItemEntity) {
        Category category = rankingItemEntity.getCategory();

        RankingItemDTO dto = new RankingItemDTO();
        dto.setId(rankingItemEntity.getId());
        dto.setName(rankingItemEntity.getName());
        if (Objects.nonNull(category)) {
            dto.setCategoryTypeId(category.getId());
        }

        return dto;
    }

    private RankingItem toEntity(RankingItemDTO rankingItemDTO) {

        RankingItem rankingItem = new RankingItem();
        rankingItem.setId(rankingItemDTO.getId());
        rankingItem.setName(rankingItemDTO.getName());
        if (rankingItemDTO.getCategoryTypeId() != null) {
            Category category = categoryService.getCategoryById(rankingItemDTO.getCategoryTypeId());
            rankingItem.setCategory(category);
        }
        return rankingItem;
    }
}
