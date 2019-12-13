package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.CategoryDto;
import com.udelphi.exception.EntityNotFoundException;
import com.udelphi.model.Category;
import com.udelphi.repository.CategoryRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category saveCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(saveCategory, CategoryDto.class);
    }

    public CategoryDto getCategory(int id) {
        return categoryRepository.findById(id)
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    public void updateCategory(int id, CategoryDto categoryDto) {
        categoryRepository.findById(id)
                .map(category -> modelMapper.map(categoryDto, Category.class))
                .ifPresentOrElse(categoryRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Entity not found with id: " + id);
                        });
    }
}
