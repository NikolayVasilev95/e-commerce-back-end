package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("select p from Product p where name like %?1% and subcategory_id like %?2%")
  Page<Product> findByNameAndSubcategoryId(String name, Long subcategoryId, Pageable pageable);
}
