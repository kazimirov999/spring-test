package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.CategoryDto;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto categoryDto);

    CategoryDto getCategory(int id);

    List<CategoryDto> getAllCategories();

    void deleteById(int id);

    void updateCategory(int id, CategoryDto categoryDto);
}
