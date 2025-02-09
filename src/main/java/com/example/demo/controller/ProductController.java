package com.example.demo.controller;


import com.example.demo.dto.ProductSaveDTO;
import com.example.demo.entity.Product;
import com.example.demo.repo.AttachmentRepository;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository, AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/bycategory/{id}")
    public List<Product> getProducts(@PathVariable Integer id) {
        if (id != null) {
            return productRepository.findAllByCategory_Id(id);
        } else {
            return productRepository.findAll();
        }
    }

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable Integer id) {
        return productRepository.findById(id).orElseThrow();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void save(@RequestBody ProductSaveDTO productSaveDTO) {
        Product product = Product.builder()
                .name(productSaveDTO.getName())
                .price(productSaveDTO.getPrice())
                .category(categoryRepository.findById(productSaveDTO.getCategoryId()).orElseThrow())
                .photo(attachmentRepository.findById(productSaveDTO.getAttachmentId()).orElseThrow())
                .build();
        productRepository.save(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void update(@RequestBody ProductSaveDTO productSaveDTO, @PathVariable Integer id) {
        Product product = Product.builder()
                .id(id)
                .name(productSaveDTO.getName())
                .price(productSaveDTO.getPrice())
                .category(categoryRepository.findById(productSaveDTO.getCategoryId()).orElseThrow())
                .photo(attachmentRepository.findById(productSaveDTO.getAttachmentId()).orElseThrow())
                .build();

        productRepository.save(product);
    }
}
