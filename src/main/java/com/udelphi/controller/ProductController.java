package com.udelphi.controller;

import java.util.List;
import com.udelphi.dto.CategoryDto;
import com.udelphi.dto.CommentDto;
import com.udelphi.dto.OrderItemDto;
import com.udelphi.dto.ProductDto;
import com.udelphi.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a product")
    public ProductDto createProduct(
            @ApiParam(value = "Product object store in database table", required = true)
            @RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a product by Id")
    public ProductDto getProduct(
            @ApiParam(value = "Product id from which product object will retrieve", required = true)
            @PathVariable int id) {
        return productService.getProduct(id);
    }

    @GetMapping
    @ApiOperation(value = "View a list of available products", response = List.class)
    public List<ProductDto> getRoles() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a product")
    public void deleteProduct(
            @ApiParam(value = "Product Id from which product object will delete from database table", required = true)
            @PathVariable int id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a product")
    public void updateProduct(
            @ApiParam(value = "Product Id to update product object", required = true)
            @PathVariable int id,
            @ApiParam(value = "Update product object", required = true)
            @RequestBody ProductDto productDto) {
        productService.updateProduct(id, productDto);
    }

    @GetMapping("/{id}/categories")
    @ApiOperation(value = "View a list of available product categories", response = List.class)
    public List<CategoryDto> getProductCategories(
            @ApiParam(value = "Product Id from which categories objects will retrieve", required = true)
            @PathVariable int id) {
        return productService.getProductCategories(id);
    }

    @GetMapping("/{id}/comments")
    @ApiOperation(value = "View a list of available product comments", response = List.class)
    public List<CommentDto> getProductComments(
            @ApiParam(value = "Product Id from which comments objects will retrieve", required = true)
            @PathVariable int id) {
        return productService.getProductComments(id);
    }


    @GetMapping("/{id}/orderItems")
    @ApiOperation(value = "View a list of available product order items", response = List.class)
    public List<OrderItemDto> getProductOrderItems(
            @ApiParam(value = "Product Id from which order items objects will retrieve", required = true)
            @PathVariable int id) {
        return productService.getProductOrderItems(id);
    }
}
