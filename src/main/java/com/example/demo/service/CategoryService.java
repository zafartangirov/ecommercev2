package com.example.demo.service;


import com.example.demo.dto.CategorySaveDTO;
import com.example.demo.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

    Category getOneCategoryById(Integer id);

    void deleteCategoryById(Integer id);

    void save(CategorySaveDTO categorySaveDTO);

    void update(CategorySaveDTO categorySaveDTO, Integer id);
}
