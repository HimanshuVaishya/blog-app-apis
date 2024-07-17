package com.codewithhimanshu.blog.blogappapis.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private long categoryId;
    @NotEmpty(message = "title should not be empty")
    private String categoryTitle;
    @NotEmpty(message = "please enter description about category")
    private String categoryDescription;
}
