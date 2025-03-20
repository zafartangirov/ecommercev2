package com.example.demo.repo;

import com.example.demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindAllByCategoryId() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100);
        product = productRepository.save(product);

        List<Product> products = productRepository.findAllByCategory_Id(product.getCategory().getId());
        assertNotNull(products);
    }
}