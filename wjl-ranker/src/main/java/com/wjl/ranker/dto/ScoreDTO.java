package com.wjl.ranker.dto;

import com.wjl.ranker.validations.OnUpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static com.wjl.ranker.Constants.VALIDATION_NOT_BLANK;
import static com.wjl.ranker.Constants.VALIDATION_NOT_NULL;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ScoreDTO {
    @NotNull(message = VALIDATION_NOT_NULL)
    private Long userAccountId;
    @NotNull(message = VALIDATION_NOT_NULL)
    private Long rankingItemId;

    private Long categoryId;

    private Integer scoreValue;
}
