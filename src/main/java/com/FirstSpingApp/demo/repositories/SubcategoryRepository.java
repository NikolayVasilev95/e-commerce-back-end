package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
  @Query("select s from Subcategory s where name like %?1% and category_id like %?2%")
  Page<Subcategory> findByNameAndCategoryId(String name, Long categoryId, Pageable pageable);
}
