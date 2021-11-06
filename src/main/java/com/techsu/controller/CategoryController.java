package com.techsu.controller;

import com.techsu.model.Category;
import com.techsu.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public void saveCategory(@PathVariable Category category) {
        categoryService.save(category);
    }


    @GetMapping
    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }


}
