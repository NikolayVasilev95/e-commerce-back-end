package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubcategoryService {

  Page<Subcategory> getAllSubcategoriesPageable(String name, Long categoryId, Pageable pageable);

  List<Subcategory> getAllSubcategories();

  Subcategory getCategory(long id);

  Subcategory create(Subcategory subcategory);

  Subcategory update(Subcategory subcategory);

  void delete(Long id);
}
