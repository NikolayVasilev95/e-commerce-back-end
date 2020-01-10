package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.Category;
import com.FirstSpingApp.demo.domain.Subcategory;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.SubcategoryRepository;
import com.FirstSpingApp.demo.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {
  @Autowired private SubcategoryRepository subcategoryRepository;

  @Override
  public Page<Subcategory> getAllSubcategoriesPageable(
      String name, Long categoryId, Pageable pageable) {
    return subcategoryRepository.findByNameAndCategoryId(name, categoryId, pageable);
  }

  @Override
  public List<Subcategory> getAllSubcategories() {
    return subcategoryRepository.findAll();
  }

  @Override
  public Subcategory getCategory(long id) {
    return subcategoryRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Subcategory not found"));
  }

  @Override
  public Subcategory create(Subcategory subcategory) {
    return subcategoryRepository.save(subcategory);
  }

  @Override
  public Subcategory update(Subcategory subcategory) {
    return subcategoryRepository.save(subcategory);
  }

  @Override
  public void delete(Long id) {
    getCategory(id);
    subcategoryRepository.deleteById(id);
  }
}
