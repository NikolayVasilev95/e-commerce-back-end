package com.FirstSpingApp.demo.controllers;

import com.FirstSpingApp.demo.domain.Product;
import com.FirstSpingApp.demo.repositories.ProductRepository;
import com.FirstSpingApp.demo.services.ProductService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class ProductController {

  @Autowired private ProductService productService;

  /** USER API */
  @GetMapping("/api/user/product")
  public Product showProduct(
      @RequestParam(value = "id", required = true) @Positive Long productId) {
    Product result = productService.getProduct(productId);
    return result;
  }

  @GetMapping("/api/user/product-page")
  public Page<Product> showPage(
      @RequestParam Optional<String> name,
      @RequestParam Optional<Long> subcategoryId,
      @RequestParam @Positive Optional<Integer> page,
      @RequestParam Optional<String> sortBy) {
    Page<Product> result =
        productService.getAllProductsPageable(
            name.orElse("_"),
            subcategoryId.orElse(1L),
            PageRequest.of(page.orElse(0), 8, Sort.Direction.ASC, sortBy.orElse("id")));
    return result;
  }

  /** ADMIN API */
  @GetMapping("/api/admin/products")
  public List<Product> showProductsForAdmin() {
    return productService.getAllProducts();
  }

  @PostMapping(value = "/api/admin/product/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addProduct(@RequestBody @Valid Product product) {
    productService.create(product);
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson("Successfully added!"));
  }

  @DeleteMapping(value = "/api/admin/product/delete")
  public ResponseEntity deleteProduct(
      @RequestParam(value = "id", required = true) @Positive Long id) {
    productService.delete(id);
    Gson gson = new Gson();
    return ResponseEntity.ok(gson.toJson("Category successfully delete!"));
  }
}
