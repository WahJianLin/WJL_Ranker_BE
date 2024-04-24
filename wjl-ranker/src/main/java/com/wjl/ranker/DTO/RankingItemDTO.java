package com.wjl.ranker.DTO;

import com.wjl.ranker.entities.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingItemDTO {
    private Long id;
    private String name;
    private Category category;
}