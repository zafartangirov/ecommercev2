package com.example.demo.repo;

import com.example.demo.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setName("Test Category");
        Category savedCategory = categoryRepository.save(category);
        assertNotNull(savedCategory);
    }

    @Test
    public void testFindCategoryById() {
        Category category = new Category();
        category.setName("Test Category");
        category = categoryRepository.save(category);

        Category fetchedCategory = categoryRepository.findById(category.getId()).orElseThrow();
        assertNotNull(fetchedCategory);
        assertEquals("Test Category", fetchedCategory.getName());
    }
}
