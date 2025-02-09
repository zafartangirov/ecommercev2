package com.example.demo.controller;


import com.example.demo.dto.CategorySaveDTO;
import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Category getOneCategory(@PathVariable Integer id){
        return categoryRepository.findById(id).orElseThrow();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void save(@RequestBody CategorySaveDTO categorySaveDTO){
        Category category = Category.builder()
                .name(categorySaveDTO.getName())
                .build();
        categoryRepository.save(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void update(@RequestBody CategorySaveDTO categorySaveDTO, @PathVariable Integer id){
        Category category = Category.builder()
                .id(id)
                .name(categorySaveDTO.getName())
                .build();
        categoryRepository.save(category);
    }
}
