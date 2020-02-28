package com.example.demo.controllers;

import com.example.demo.dto.ProductDto;

import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ApiOperation(value = "Get all products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get product by id")
    public ProductDto getProductById(
            @ApiParam(value = "The product ID that will return the product from the database table")
            @PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a product")
    public ProductDto addProduct(
            @ApiParam(value = "Product object store in database table", required = true)
            @RequestBody ProductDto product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete product by id")
    public void deleteProductDyId(
            @ApiParam(value = "Product ID to delete the product from the database table")
            @PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all products")
    public void deleteProductAll() {
        productService.deleteProductAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update product by id")
    public void updateProductById(
            @ApiParam(value = "Product ID to be updated in the database table")
            @PathVariable Integer id,
            @ApiParam(value = "Update product object")
            @RequestBody ProductDto productDto) {
        productService.updateProduct(id, productDto);
    }
}
