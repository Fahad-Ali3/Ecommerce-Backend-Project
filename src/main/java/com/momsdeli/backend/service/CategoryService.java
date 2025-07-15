package com.momsdeli.backend.service;

import com.momsdeli.backend.dto.CategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {


     CategoryDTO createCategory(CategoryDTO categoryDTO);
     CategoryDTO updateCategory(Long categoryId,CategoryDTO categoryDTO);
     CategoryDTO deleteCategory(Long categoryId);
     CategoryDTO getCategoryById(Long categoryId);
     Page<CategoryDTO> getAllCategories(int page, int size, String sortBy);
}
