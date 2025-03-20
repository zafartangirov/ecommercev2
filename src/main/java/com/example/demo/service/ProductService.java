package com.example.demo.service;


import com.example.demo.dto.ProductSaveDTO;
import com.example.demo.entity.Product;
import com.example.demo.repo.AttachmentRepository;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public List<Product> getProductsByCategoryId(Integer id){
        if (id != null) {
            return productRepository.findAllByCategory_Id(id);
        } else {
            return productRepository.findAll();
        }
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getOneProductById(Integer id){
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }

    public void save(ProductSaveDTO productSaveDTO){
        Product product = Product.builder()
                .name(productSaveDTO.getName())
                .price(productSaveDTO.getPrice())
                .category(categoryRepository.findById(productSaveDTO.getCategoryId()).orElseThrow())
                .photo(attachmentRepository.findById(productSaveDTO.getAttachmentId()).orElseThrow())
                .build();
        productRepository.save(product);
    }

    public void update(ProductSaveDTO productSaveDTO, Integer id){
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
