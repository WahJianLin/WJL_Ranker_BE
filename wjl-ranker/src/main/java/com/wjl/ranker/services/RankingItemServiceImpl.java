package com.wjl.ranker.services;

import com.wjl.ranker.DTO.RankingItemDTO;
import com.wjl.ranker.entities.RankingItem;
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
    public RankingItemDTO createRankingItem(RankingItem rankingItemEntity) {
        //TODO create validator that validates the RankingItem before creation
        try {
            return toDTO(rankingItemRepo.save(rankingItemEntity));
        } catch (Exception e) {
            throw new RuntimeException("Item failed to Save");
        }
    }

    @Override
    public RankingItemDTO updateRankingItem(RankingItem rankingItemEntity) {
        //TODO create validator that validates the RankingItem before update
        RankingItem item = getRankingItem(rankingItemEntity.getId());
        try {
            item.setName(rankingItemEntity.getName());
            item.setCategory(rankingItemEntity.getCategory());
            rankingItemRepo.save(item);
        } catch (Exception e) {
            throw new RuntimeException("Item failed to update");
        }
        return toDTO(item);
    }

    @Override
    public void deleteRankingItem(long id) {
        if (!rankingItemRepo.existsById(id)) {
            throw new IllegalStateException("Item " + id + " does not exist");
        } else {
            rankingItemRepo.deleteById(id);
        }
    }

    private RankingItemDTO toDTO(RankingItem rankingItemEntity) {
        RankingItemDTO dto = new RankingItemDTO();
        dto.setId(rankingItemEntity.getId());
        dto.setName(rankingItemEntity.getName());
        dto.setCategory(rankingItemEntity.getCategory());
        return dto;
    }

    private RankingItem getRankingItem(Long id) {
        return rankingItemRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }
}
