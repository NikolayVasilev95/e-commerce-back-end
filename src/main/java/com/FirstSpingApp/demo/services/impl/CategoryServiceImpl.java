package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.Category;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.CategoryRepository;
import com.FirstSpingApp.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired private CategoryRepository categoryRepository;

  @Override
  public Page<Category> getAllCategoriesPageable(String name, Pageable pageable) {
    return categoryRepository.findByName(name, pageable);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category getCategory(long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
  }

  @Override
  public Category create(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public Category update(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public void delete(Long id) {
    getCategory(id);
    categoryRepository.deleteById(id);
  }
}
