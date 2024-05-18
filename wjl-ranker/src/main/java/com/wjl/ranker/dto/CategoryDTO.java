package com.wjl.ranker.dto;

import com.wjl.ranker.validations.OnUpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.wjl.ranker.Constants.VALIDATION_NOT_BLANK;
import static com.wjl.ranker.Constants.VALIDATION_NOT_NULL;

@Data
public class CategoryDTO {
    @NotNull(groups = OnUpdateValidation.class, message = VALIDATION_NOT_NULL)
    private Long id;

    @NotBlank(message = VALIDATION_NOT_BLANK)
    private String name;
    private String description;
}
