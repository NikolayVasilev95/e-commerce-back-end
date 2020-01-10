package com.FirstSpingApp.demo.controllers;

import com.FirstSpingApp.demo.domain.Subcategory;
import com.FirstSpingApp.demo.services.SubcategoryService;
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
public class SubcategoryController {

  @Autowired private SubcategoryService subcategoryService;

  /** USER API */
  @GetMapping("/api/user/subcategory-page")
  public Page<Subcategory> showPage(
      @RequestParam Optional<String> name,
      @RequestParam Optional<Long> categoryId,
      @RequestParam @Positive Optional<Integer> page,
      @RequestParam Optional<String> sortBy) {
    return subcategoryService.getAllSubcategoriesPageable(
        name.orElse("_"),
        categoryId.orElse(1L),
        PageRequest.of(page.orElse(0), 8, Sort.Direction.ASC, sortBy.orElse("id")));
  }

  @GetMapping("/api/user/subcategories")
  public List<Subcategory> showAllSubcategories() {
    return subcategoryService.getAllSubcategories();
  }

  /** ADMIN API */
  @GetMapping("/api/admin/subcategories")
  public List<Subcategory> showAllSubcategoriesForAdmin() {
    return subcategoryService.getAllSubcategories();
  }

  @PostMapping(value = "/api/admin/subcategory/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addSubcategory(@RequestBody @Valid Subcategory subcategory) {
    subcategoryService.create(subcategory);
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson("Successfully added!"));
  }

  @DeleteMapping(value = "/api/admin/subcategory/delete")
  public ResponseEntity deleteSubcategory(
      @RequestParam(value = "id", required = true) @Positive Long id) {
    subcategoryService.delete(id);
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson("Category successfully delete!"));
  }
}
