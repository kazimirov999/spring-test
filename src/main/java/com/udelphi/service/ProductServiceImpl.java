package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.CategoryDto;
import com.udelphi.dto.CommentDto;
import com.udelphi.dto.OrderItemDto;
import com.udelphi.dto.ProductDto;
import com.udelphi.exception.EntityNotFoundException;
import com.udelphi.model.Product;
import com.udelphi.repository.CategoryRepository;
import com.udelphi.repository.CommentRepository;
import com.udelphi.repository.OrderItemRepository;
import com.udelphi.repository.ProductRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderItemRepository orderItemRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              OrderItemRepository orderItemRepository,
                              CommentRepository commentRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.orderItemRepository = orderItemRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }


    public ProductDto saveProduct(ProductDto productDto) {
        Product saveProduct = productRepository.save(modelMapper.map(productDto, Product.class));
        return modelMapper.map(saveProduct, ProductDto.class);
    }

    public ProductDto getProduct(int id) {

        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(int id, ProductDto productDto) {
        productRepository.findById(id)
                .map(product -> modelMapper.map(productDto, Product.class))
                .ifPresentOrElse(productRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Entity not found with id: " + id);
                        });
    }

    public List<CategoryDto> getProductCategories(int id) {
        return categoryRepository.findCategoriesByProductId(id)
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(toList());
    }

    public List<CommentDto> getProductComments(int id) {
        return commentRepository.findAllByProductId(id)
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(toList());
    }

    public List<OrderItemDto> getProductOrderItems(int id) {
        return orderItemRepository.findAllByProductId(id)
                .stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                .collect(toList());
    }
}
