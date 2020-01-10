package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.Product;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.ProductRepository;
import com.FirstSpingApp.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired private ProductRepository productRepository;

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProduct(long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
  }

  @Override
  public Product create(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void delete(Long id) {
    getProduct(id);
    productRepository.deleteById(id);
  }

  @Override
  public Page<Product> getAllProductsPageable(String name, Long subcategoryId, Pageable pageable) {
    return productRepository.findByNameAndSubcategoryId(name, subcategoryId, pageable);
  }
}
