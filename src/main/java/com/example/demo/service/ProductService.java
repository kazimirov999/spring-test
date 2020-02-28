package com.example.demo.service;

import com.example.demo.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Integer id);

    void updateProduct(Integer id, ProductDto productDto);

    void deleteProductById(Integer id);

    void deleteProductAll();

}
