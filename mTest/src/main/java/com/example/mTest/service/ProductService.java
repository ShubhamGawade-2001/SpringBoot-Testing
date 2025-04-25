package com.example.mTest.service;

import com.example.mTest.entities.Category;
import com.example.mTest.entities.Product;
import com.example.mTest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.FileNameMap;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Value("${app.pagination.default-page-size}")
    private int defaultPageSize;

    public Page<Product> getAll(int page){
        Pageable pageable = PageRequest.of(page,defaultPageSize, Sort.by("id"));
        return  productRepository.findAll(pageable);
    }
    public Product getById(Long id){
        return  productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product Not Found"));
    }
    public Product create(Product product){
        Category category = categoryService.getById(product.getCategory().getId());
        product.setCategory(category);
        return productRepository.save(product);
    }
    public Product update(Long id,Product product ){
        Product existing=getById(id);
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        Category category= categoryService.getById((product.getCategory().getId()));
        existing.setCategory(category);
        return productRepository.save(existing);
    }
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
