package com.example.demo.service;


import com.example.demo.dto.ProductSaveDTO;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getProductsByCategoryId(Integer id);

    List<Product> getAllProducts();

    Product getOneProductById(Integer id);

    void deleteProductById(Integer id);

    void save(ProductSaveDTO productSaveDTO);

    void update(ProductSaveDTO productSaveDTO, Integer id);
}
