package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

  List<Product> getAllProducts();

  Product getProduct(long id);

  Product create(Product product);

  Product update(Product product);

  void delete(Long id);

  Page<Product> getAllProductsPageable(String name, Long subcategoryId, Pageable pageable);
}
