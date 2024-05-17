package com.wjl.ranker.services;

import com.wjl.ranker.Constants;
import com.wjl.ranker.entities.RankingItem;
import com.wjl.ranker.exception.GeneralException;
import com.wjl.ranker.repositories.RankingItemRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RankingItemServiceImpl implements RankingItemService {
    // TODO create validator
    public static final String ENTITY = "ITEM";

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
        try {
            log.info((String.format(Constants.LOG_ATTEMPTING_TO_SAVE, ENTITY, rankingItemEntity.getId(), rankingItemEntity)));
            return rankingItemRepo.save(rankingItemEntity);
        } catch (Exception e) {
            log.error((String.format(Constants.LOG_FAILED_SAVE, ENTITY, rankingItemEntity.getId(), rankingItemEntity)));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_SAVE, ENTITY));
        }
    }

    @Override
    public RankingItem updateRankingItem(RankingItem rankingItemEntity) {
        RankingItem item = getRankingItem(rankingItemEntity.getId());
        try {
            log.info((String.format(Constants.LOG_ATTEMPTING_TO_UPDATE, ENTITY, rankingItemEntity.getId(), rankingItemEntity)));

            item.setName(rankingItemEntity.getName());
            item.setCategory(rankingItemEntity.getCategory());
            rankingItemRepo.save(item);
        } catch (Exception e) {
            log.error((String.format(Constants.LOG_FAILED_UPDATE, ENTITY, rankingItemEntity.getId(), rankingItemEntity)));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_UPDATE, ENTITY));
        }
        return item;
    }

    @Override
    public void deleteRankingItem(long id) {
        log.info(String.format(Constants.LOG_ATTEMPTING_TO_DELETE, ENTITY, id));
        if (!rankingItemRepo.existsById(id)) {
            log.error(String.format(Constants.LOG_FAILED_DELETE, ENTITY, id));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_DELETE, ENTITY));
        } else {
            rankingItemRepo.deleteById(id);
            log.info(String.format(Constants.LOG_SUCCESSFUL_DELETE, ENTITY, id));
        }
    }

    private RankingItem getRankingItem(Long id) {
        return rankingItemRepo.findById(id).orElseThrow(() -> {
            log.error(String.format(Constants.LOG_FAILED_FIND, ENTITY, id));
            return new GeneralException(String.format(Constants.EXCEPTION_GENERAL_NOT_FOUND, ENTITY));
        });
    }
}
