package com.codewithhimanshu.blog.blogappapis.services;

import com.codewithhimanshu.blog.blogappapis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(long categoryId, CategoryDto categoryDto);
    CategoryDto getCategoryById(long categoryId);
    List<CategoryDto> getALlCategories();
    void deleteCategory(long categoryId);
}
