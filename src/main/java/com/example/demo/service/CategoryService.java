package com.example.demo.service;


import com.example.demo.dto.CategorySaveDTO;
import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getOneCategoryById(Integer id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public void deleteCategoryById(Integer id){
        categoryRepository.deleteById(id);
    }

    public void save(CategorySaveDTO categorySaveDTO){
        Category category = Category.builder()
                .name(categorySaveDTO.getName())
                .build();
        categoryRepository.save(category);
    }

    public void update(CategorySaveDTO categorySaveDTO, Integer id){
        Category category = Category.builder()
                .id(id)
                .name(categorySaveDTO.getName())
                .build();
        categoryRepository.save(category);
    }
}
