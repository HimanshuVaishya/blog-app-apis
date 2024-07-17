package com.codewithhimanshu.blog.blogappapis.services.Impl;

import com.codewithhimanshu.blog.blogappapis.entity.Category;
import com.codewithhimanshu.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithhimanshu.blog.blogappapis.payloads.CategoryDto;
import com.codewithhimanshu.blog.blogappapis.repositories.CategoryRepository;
import com.codewithhimanshu.blog.blogappapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category createdCat = this.categoryRepository.save(cat);
        return this.modelMapper.map(createdCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) {
        Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," id ", categoryId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCat = this.categoryRepository.save(cat);
        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(long categoryId) {
        Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getALlCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public void deleteCategory(long categoryId) {
        Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", " id ",categoryId));
        this.categoryRepository.delete(cat);
    }
}
