package com.techsu.service;

import com.techsu.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category save(Category category);

    List<Category> findAllCategories();

    Category getCategoryById(Long id);


}
