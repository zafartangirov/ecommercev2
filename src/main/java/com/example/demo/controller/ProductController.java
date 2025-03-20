package com.example.demo.controller;


import com.example.demo.dto.ProductSaveDTO;
import com.example.demo.entity.Product;
import com.example.demo.repo.AttachmentRepository;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/bycategory/{id}")
    public List<Product> getProducts(@PathVariable Integer id) {
        return productService.getProductsByCategoryId(id);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable Integer id) {
        return productService.getOneProductById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void save(@RequestBody ProductSaveDTO productSaveDTO) {
        productService.save(productSaveDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void update(@RequestBody ProductSaveDTO productSaveDTO, @PathVariable Integer id) {
        productService.update(productSaveDTO, id);
    }
}
