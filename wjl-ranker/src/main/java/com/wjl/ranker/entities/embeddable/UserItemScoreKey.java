package com.wjl.ranker.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserItemScoreKey implements Serializable {
    @Column(name = "user_account_id")
    Long userAccountId;

    @Column(name = "ranking_item_id")
    Long rankingItemId;

}
