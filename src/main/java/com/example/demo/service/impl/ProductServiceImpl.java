package com.example.demo.service.impl;

import com.example.demo.dto.ProductSaveDTO;
import com.example.demo.entity.Product;
import com.example.demo.repo.AttachmentRepository;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public List<Product> getProductsByCategoryId(Integer id) {
        if (id != null) {
            return productRepository.findAllByCategory_Id(id);
        } else {
            return productRepository.findAll();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getOneProductById(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public void save(ProductSaveDTO productSaveDTO) {
        Product product = Product.builder()
                .name(productSaveDTO.getName())
                .price(productSaveDTO.getPrice())
                .category(categoryRepository.findById(productSaveDTO.getCategoryId()).orElseThrow())
                .photo(attachmentRepository.findById(productSaveDTO.getAttachmentId()).orElseThrow())
                .build();
        productRepository.save(product);
    }

    @Override
    public void update(ProductSaveDTO productSaveDTO, Integer id) {
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
