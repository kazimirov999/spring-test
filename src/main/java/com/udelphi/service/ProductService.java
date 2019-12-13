package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.CategoryDto;
import com.udelphi.dto.CommentDto;
import com.udelphi.dto.OrderItemDto;
import com.udelphi.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(ProductDto productDto);

    ProductDto getProduct(int id);

    List<ProductDto> getAllProducts();

    void deleteById(int id);

    void updateProduct(int id, ProductDto productDto);

    List<CategoryDto> getProductCategories(int id);

    List<CommentDto> getProductComments(int id);

    List<OrderItemDto> getProductOrderItems(int id);
}
