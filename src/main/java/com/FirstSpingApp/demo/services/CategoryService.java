package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

  Page<Category> getAllCategoriesPageable(String name, Pageable pageable);

  List<Category> getAllCategories();

  Category getCategory(long id);

  Category create(Category category);

  Category update(Category category);

  void delete(Long id);
}
