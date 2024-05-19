package com.wjl.ranker.entities;

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
    private Integer scoreValue;
    @ManyToOne
    @MapsId("userAccountId")
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne
    @MapsId("rankingItemId")
    @JoinColumn(name = "ranking_item_id")
    private RankingItem rankingItem;
}
