package com.example.demo.service;

import com.example.demo.dto.ProductSaveDTO;
import com.example.demo.entity.Attachment;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repo.AttachmentRepository;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private AttachmentRepository attachmentRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testSaveProduct() {
        ProductSaveDTO productSaveDTO = new ProductSaveDTO();
        productSaveDTO.setName("Test Product");
        productSaveDTO.setPrice(100);
        productSaveDTO.setCategoryId(1);
        productSaveDTO.setAttachmentId(1);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(new Category()));
        when(attachmentRepository.findById(1)).thenReturn(Optional.of(new Attachment()));

        productService.save(productSaveDTO);
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
