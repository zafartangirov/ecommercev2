package com.example.demo.service.impl;


import com.example.demo.dto.CategorySaveDTO;
import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getOneCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void save(CategorySaveDTO categorySaveDTO) {
        Category category = Category.builder()
                .name(categorySaveDTO.getName())
                .build();
        categoryRepository.save(category);
    }

    @Override
    public void update(CategorySaveDTO categorySaveDTO, Integer id) {
        Category category = Category.builder()
                .id(id)
                .name(categorySaveDTO.getName())
                .build();
        categoryRepository.save(category);
    }
}
