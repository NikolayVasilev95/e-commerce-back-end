package com.FirstSpingApp.demo.controllers;

import com.FirstSpingApp.demo.domain.Category;
import com.FirstSpingApp.demo.services.CategoryService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

  @Autowired private CategoryService categoryService;

  /** USER API */
  @GetMapping("/api/user/category-page")
  public Page<Category> showPage(
      @RequestParam Optional<String> name,
      @RequestParam @Positive Optional<Integer> page,
      @RequestParam Optional<String> sortBy) {
    return categoryService.getAllCategoriesPageable(
        name.orElse("_"),
        PageRequest.of(page.orElse(0), 8, Sort.Direction.ASC, sortBy.orElse("id")));
  }

  @GetMapping("/api/user/categories")
  public List<Category> showAllCategories() {
    return categoryService.getAllCategories();
  }

  /** ADMIN API */
  @GetMapping("/api/admin/categories")
  public List<Category> showAllCategoriesForAdmin() {
    return categoryService.getAllCategories();
  }

  @PostMapping(value = "/api/admin/category/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addCategory(@RequestBody @Valid Category category) {
    categoryService.create(category);
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson("Successfully added!"));
  }

  @DeleteMapping(value = "/api/admin/category/delete")
  public ResponseEntity deleteCategory(
      @RequestParam(value = "id", required = true) @Positive Long id) {
    categoryService.delete(id);
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson("Category successfully delete!"));
  }
}
