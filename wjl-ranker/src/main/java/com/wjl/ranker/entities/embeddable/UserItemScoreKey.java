package com.wjl.ranker.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserItemScoreKey implements Serializable {
    @Column(name = "user_account_id")
    Long userAccountId;

    @Column(name = "ranking_item_id")
    Long rankingItemId;

}
