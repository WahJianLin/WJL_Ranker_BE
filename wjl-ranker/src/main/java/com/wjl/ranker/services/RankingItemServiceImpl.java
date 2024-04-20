package com.wjl.ranker.services;

import com.wjl.ranker.entities.RankingItem;
import com.wjl.ranker.repositories.RankingItemRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingItemServiceImpl implements RankingItemService{

    final RankingItemRepo rankingItemRepo;

    @Autowired
    public RankingItemServiceImpl(RankingItemRepo rankingItemRepo) {
        this.rankingItemRepo = rankingItemRepo;
    }

    @Override
    public List<RankingItem> getAllRankingItems() {
        return rankingItemRepo.findAll();
    }

    @Override
    public RankingItem getRankingItem(Long id) {
        return rankingItemRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Item not found"));
    }

    @Override
    public RankingItem createRankingItem(RankingItem rankingItem) {
        //TODO create validator that validates the RankingItem before creation
        try {
            return rankingItemRepo.save(rankingItem);
        }catch (Exception e) {
            throw new RuntimeException("Item Failed to Save");
        }
    }

    @Override
    public RankingItem updateRankingItem(RankingItem rankingItem) {
        //TODO create validator that validates the RankingItem before update
        RankingItem item = getRankingItem(rankingItem.getId());
        try {
            item.setName(rankingItem.getName());
            item.setCategoryType(rankingItem.getCategoryType());
            rankingItemRepo.save(item);
        }catch (Exception e) {
            throw new RuntimeException("Item Failed to Save");
        }
        return item;
    }

    @Override
    public void deleteRankingItem(long id) {

        if(!rankingItemRepo.existsById(id)){
            throw new IllegalStateException("item " + id + " does not exist");
        } else {
            rankingItemRepo.deleteById(id);
        }
    }
}
