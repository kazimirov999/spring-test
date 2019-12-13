package com.udelphi.controller;

import java.util.List;
import com.udelphi.dto.CategoryDto;
import com.udelphi.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a category")
    public CategoryDto createCategory(
            @ApiParam(value = "Category object store in database table", required = true)
            @RequestBody CategoryDto categoryDto){
        return categoryService.saveCategory(categoryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a category by Id")
    public CategoryDto getCategory(
            @ApiParam(value = "Category id from which category object will retrieve", required = true)
            @PathVariable int id){
        return categoryService.getCategory(id);
    }

    @GetMapping
    @ApiOperation(value = "View a list of available categories", response = List.class)
    public List<CategoryDto> getCategories(){
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a category")
    public void deleteCategory(
            @ApiParam(value = "Category Id from which category object will delete from database table", required = true)
            @PathVariable int id){
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a category")
    public void updateCategory(
            @ApiParam(value = "Category Id to update category object", required = true)
            @PathVariable int id,
            @ApiParam(value = "Update category object", required = true)
            @RequestBody CategoryDto categoryDto){
        categoryService.updateCategory(id, categoryDto);
    }
}
