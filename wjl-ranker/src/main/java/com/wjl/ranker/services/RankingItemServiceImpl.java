package com.wjl.ranker.services;

import com.wjl.ranker.DTO.RankingItemDTO;
import com.wjl.ranker.entities.RankingItemEntity;
import com.wjl.ranker.repositories.RankingItemRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingItemServiceImpl implements RankingItemService {

    final RankingItemRepo rankingItemRepo;

    @Autowired
    public RankingItemServiceImpl(RankingItemRepo rankingItemRepo) {
        this.rankingItemRepo = rankingItemRepo;
    }

    @Override
    public List<RankingItemDTO> getAllRankingItems() {
        return rankingItemRepo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public RankingItemDTO getRankingItemById(Long id) {
        return toDTO(getRankingItem(id));
    }

    @Override
    public RankingItemDTO createRankingItem(RankingItemEntity rankingItemEntity) {
        //TODO create validator that validates the RankingItem before creation
        try {
            return toDTO(rankingItemRepo.save(rankingItemEntity));
        } catch (Exception e) {
            throw new RuntimeException("Item Failed to Save");
        }
    }

    @Override
    public RankingItemDTO updateRankingItem(RankingItemEntity rankingItemEntity) {
        //TODO create validator that validates the RankingItem before update
        RankingItemEntity item = getRankingItem(rankingItemEntity.getId());
        try {
            item.setName(rankingItemEntity.getName());
            item.setCategoryType(rankingItemEntity.getCategoryType());
            rankingItemRepo.save(item);
        } catch (Exception e) {
            throw new RuntimeException("Item Failed to Save");
        }
        return toDTO(item);
    }

    @Override
    public void deleteRankingItem(long id) {

        if (!rankingItemRepo.existsById(id)) {
            throw new IllegalStateException("item " + id + " does not exist");
        } else {
            rankingItemRepo.deleteById(id);
        }
    }

    private RankingItemDTO toDTO(RankingItemEntity rankingItemEntity) {
        RankingItemDTO dto = new RankingItemDTO();
        dto.setId(rankingItemEntity.getId());
        dto.setName(rankingItemEntity.getName());
        dto.setCategoryType(rankingItemEntity.getCategoryType());
        return dto;
    }

    private RankingItemEntity getRankingItem(Long id) {
        return rankingItemRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }
}
