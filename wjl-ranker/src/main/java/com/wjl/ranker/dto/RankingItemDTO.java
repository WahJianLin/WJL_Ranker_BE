package com.wjl.ranker.dto;

import com.wjl.ranker.validations.OnUpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static com.wjl.ranker.Constants.VALIDATION_NOT_BLANK;
import static com.wjl.ranker.Constants.VALIDATION_NOT_NULL;

@Getter
@Setter
public class RankingItemDTO {
    @NotNull(groups = OnUpdateValidation.class, message = VALIDATION_NOT_NULL)
    private Long id;

    @NotBlank(message = VALIDATION_NOT_BLANK)
    private String name;
    @NotNull(message = VALIDATION_NOT_NULL)
    private Long categoryTypeId;
}