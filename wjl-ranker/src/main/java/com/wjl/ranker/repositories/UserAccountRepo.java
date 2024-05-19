package com.wjl.ranker.repositories;

import com.wjl.ranker.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Long> {
}
