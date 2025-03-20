package com.example.demo.controller;


import com.example.demo.dto.CategorySaveDTO;
import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Category getOneCategory(@PathVariable Integer id){
        return categoryService.getOneCategoryById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategoryById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void save(@RequestBody CategorySaveDTO categorySaveDTO){
        categoryService.save(categorySaveDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void update(@RequestBody CategorySaveDTO categorySaveDTO, @PathVariable Integer id){
        categoryService.update(categorySaveDTO, id);
    }
}
