package com.example.demo.service;

import com.example.demo.dto.CategorySaveDTO;
import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(new Category()));
        List<Category> categories = categoryService.getAllCategories();
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testSaveCategory() {
        CategorySaveDTO categorySaveDTO = new CategorySaveDTO();
        categorySaveDTO.setName("Test Category");
        categoryService.save(categorySaveDTO);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    public void testDeleteCategory() {
        categoryService.deleteCategoryById(1);
        verify(categoryRepository, times(1)).deleteById(1);
    }
}
