package com.wjl.ranker.services;

import com.wjl.ranker.entities.RankingItem;
import com.wjl.ranker.exception.GeneralException;
import com.wjl.ranker.repositories.RankingItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingItemServiceImpl implements RankingItemService {

    private final RankingItemRepo rankingItemRepo;

    @Autowired
    public RankingItemServiceImpl(RankingItemRepo rankingItemRepo) {
        this.rankingItemRepo = rankingItemRepo;
    }

    @Override
    public List<RankingItem> getAllRankingItems() {
        return rankingItemRepo.findAll();
    }

    @Override
    public RankingItem getRankingItemById(Long id) {
        return getRankingItem(id);
    }

    @Override
    public RankingItem createRankingItem(RankingItem rankingItemEntity) {
        //TODO create validator that validates the RankingItem before creation
        try {
            return rankingItemRepo.save(rankingItemEntity);
        } catch (Exception e) {
            throw new GeneralException("Item failed to Save");
        }
    }

    @Override
    public RankingItem updateRankingItem(RankingItem rankingItemEntity) {
        //TODO create validator that validates the RankingItem before update
        RankingItem item = getRankingItem(rankingItemEntity.getId());
        try {
            item.setName(rankingItemEntity.getName());
            item.setCategory(rankingItemEntity.getCategory());
            rankingItemRepo.save(item);
        } catch (Exception e) {
            throw new GeneralException("Item failed to update");
        }
        return item;
    }

    @Override
    public void deleteRankingItem(long id) {
        if (!rankingItemRepo.existsById(id)) {
            throw new GeneralException("Item " + id + " does not exist");
        } else {
            rankingItemRepo.deleteById(id);
        }
    }

    private RankingItem getRankingItem(Long id) {
        return rankingItemRepo.findById(id).orElseThrow(() -> new GeneralException("Item not found"));
    }
}
