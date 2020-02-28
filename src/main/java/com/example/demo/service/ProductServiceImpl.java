package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public ProductDto saveProduct(ProductDto productDto) {
        Product persist = productRepository.save(modelMapper.map(productDto, Product.class));
        return modelMapper.map(persist, ProductDto.class);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(toList());
    }

    public ProductDto getProductById(Integer id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDto.class))
                .orElseThrow(()-> new EntityNotFoundException("Product not found"));
    }

    public void updateProduct(Integer id, ProductDto productDto) {
        productRepository.findById(id)
                .map(product -> modelMapper.map(productDto, Product.class))
                .ifPresentOrElse(productRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Product not found by id " + id);
                        });
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public void deleteProductAll() {
        productRepository.deleteAll();
    }

}

