package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.Category;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Validated
public class SubcategoryDto {

  private Long id;
  private String name;
  @NotEmpty @UniqueElements private @Valid Category category;

  public SubcategoryDto() {}

  public SubcategoryDto(Long id, String name, Category category) {
    this.id = id;
    this.name = name;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
}
