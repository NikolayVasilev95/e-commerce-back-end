package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  @Query("select c from Category c where name like %?1%")
  Page<Category> findByName(String name, Pageable pageable);
}
