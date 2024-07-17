package com.codewithhimanshu.blog.blogappapis.controllers;

import com.codewithhimanshu.blog.blogappapis.payloads.ApiResponse;
import com.codewithhimanshu.blog.blogappapis.payloads.CategoryDto;
import com.codewithhimanshu.blog.blogappapis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCat = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCat, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable long categoryId, @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCat = this.categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.ok(updatedCat);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtos = this.categoryService.getALlCategories();
        return ResponseEntity.ok(categoryDtos);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "categoryId") long id){
        CategoryDto categoryDto = this.categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable long categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted", true), HttpStatus.OK);
    }
}
