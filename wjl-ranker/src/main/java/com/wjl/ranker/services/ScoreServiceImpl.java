package com.wjl.ranker.services;

import com.wjl.ranker.Constants;
import com.wjl.ranker.entities.Category;
import com.wjl.ranker.entities.Score;
import com.wjl.ranker.entities.embeddable.UserItemScoreKey;
import com.wjl.ranker.exceptions.GeneralException;
import com.wjl.ranker.repositories.ScoreRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    public static final String ENTITY = "SCORE";

    private final ScoreRepo scoreRepo;

    @Autowired
    public ScoreServiceImpl(ScoreRepo scoreRepo) {
        this.scoreRepo = scoreRepo;
    }

    @Override
    public List<Score> getAll() {
        return scoreRepo.findAll();
    }

    @Override
    public List<Score> getAllByCategory(Category category) {
        return scoreRepo.findScoresByCategory(category);
    }

    @Override
    public List<Score> updateBulk(List<Score> scoreList) {
        String scoreListString = scoreList.stream().map(Object::toString).toList().toString();
        try {
            log.info(String.format(Constants.LOG_ATTEMPTING_TO_UPDATE_BULK, ENTITY, scoreListString));
            return scoreRepo.saveAll(scoreList);
        } catch (Exception e) {
            log.error((String.format(Constants.LOG_FAILED_UPDATE_BULK, ENTITY, scoreListString)));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_UPDATE, ENTITY));
        }
    }

    @Override
    public void deleteBulk(List<UserItemScoreKey> compositeIdList) {
        String compositeIdListString = compositeIdList.stream().map(Object::toString).toList().toString();
        log.info(String.format(Constants.LOG_ATTEMPTING_TO_DELETE_BULK, ENTITY, compositeIdListString));

        try {
            scoreRepo.deleteAllById(compositeIdList);
            log.info(String.format(Constants.LOG_SUCCESSFUL_DELETE_BULK, ENTITY, compositeIdListString));
        } catch (Exception e){
            log.error(String.format(Constants.LOG_FAILED_DELETE_BULK, ENTITY, compositeIdListString));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_DELETE, ENTITY));
        }
    }
}
