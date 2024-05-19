package com.wjl.ranker.services;

import com.wjl.ranker.entities.UserAccount;

import java.util.List;

public interface UserAccountService {
    List<UserAccount> getAll();

    UserAccount getById(Long id);

    UserAccount create(UserAccount UserAccountEntity);

    UserAccount update(UserAccount UserAccountEntity);

    void deleteById(long id);
}
