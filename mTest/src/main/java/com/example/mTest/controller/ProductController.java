package com.example.mTest.controller;

import com.example.mTest.entities.Product;
import com.example.mTest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<Product> list(@RequestParam(defaultValue = "0") int page){
        return productService.getAll(page);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product created = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id){
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,@RequestBody Product product){
        return productService.update(id,product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

}
