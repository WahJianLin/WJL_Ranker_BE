package com.wjl.ranker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingItemDTO {
    private Long id;
    private String name;
    private Long categoryTypeId;
}