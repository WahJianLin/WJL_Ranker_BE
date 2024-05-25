package com.wjl.ranker.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wjl.ranker.entities.embeddable.UserItemScoreKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Score {
    @EmbeddedId
    private UserItemScoreKey compositeId;
    @ManyToOne
    @MapsId("userAccountId")
    @JoinColumn(name = "user_account_id")
    @ToString.Exclude
    private UserAccount userAccount;

    @ManyToOne
    @MapsId("rankingItemId")
    @JoinColumn(name = "ranking_item_id")
    @ToString.Exclude
    private RankingItem rankingItem;

    private Integer scoreValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

    public Score(UserAccount userAccount, RankingItem rankingItem, Category category, Integer scoreValue) {
        this.setCompositeId(new UserItemScoreKey(userAccount.getId(), rankingItem.getId()));
        this.setUserAccount(userAccount);
        this.setRankingItem(rankingItem);
        this.setCategory(category);
        this.setScoreValue(scoreValue);
    }
}
