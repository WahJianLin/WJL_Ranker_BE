package com.wjl.ranker.services;

import com.wjl.ranker.Constants;
import com.wjl.ranker.entities.UserAccount;
import com.wjl.ranker.exceptions.GeneralException;
import com.wjl.ranker.repositories.UserAccountRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {
    public static final String ENTITY = "USER_ACCOUNT";
    private final UserAccountRepo userAccountRepo;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountRepo.findAll();
    }

    @Override
    public UserAccount getById(Long id) {
        return userAccountRepo.findById(id).orElseThrow(() -> {
            log.error(String.format(Constants.LOG_FAILED_FIND, ENTITY, id));
            return new GeneralException(String.format(Constants.EXCEPTION_GENERAL_NOT_FOUND, ENTITY));
        });
    }

    @Override
    public UserAccount create(UserAccount userAccountEntity) {
        try {
            log.info((String.format(Constants.LOG_ATTEMPTING_TO_SAVE, ENTITY, userAccountEntity.getId(), userAccountEntity)));
            return userAccountRepo.save(userAccountEntity);
        } catch (Exception e) {
            log.error((String.format(Constants.LOG_FAILED_SAVE, ENTITY, userAccountEntity.getId(), userAccountEntity)));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_SAVE, ENTITY));
        }
    }

    @Override
    public UserAccount update(UserAccount userAccountEntity) {
        UserAccount userAccount = getById(userAccountEntity.getId());
        try {
            log.info((String.format(Constants.LOG_ATTEMPTING_TO_UPDATE, ENTITY, userAccountEntity.getId(), userAccountEntity)));

            userAccount.setName(userAccountEntity.getName());
            return userAccountRepo.save(userAccount);
        } catch (Exception e) {
            log.error((String.format(Constants.LOG_FAILED_UPDATE, ENTITY, userAccountEntity.getId(), userAccountEntity)));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_UPDATE, ENTITY));
        }
    }

    @Override
    public void deleteById(long id) {
        log.info(String.format(Constants.LOG_ATTEMPTING_TO_DELETE, ENTITY, id));
        if (!userAccountRepo.existsById(id)) {
            log.error(String.format(Constants.LOG_FAILED_DELETE, ENTITY, id));
            throw new GeneralException(String.format(Constants.EXCEPTION_GENERAL_FAILED_DELETE, ENTITY));
        }
        userAccountRepo.deleteById(id);
        log.info(String.format(Constants.LOG_SUCCESSFUL_DELETE, ENTITY, id));
    }
}
