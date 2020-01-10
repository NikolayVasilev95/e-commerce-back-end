package com.FirstSpingApp.demo.resources;

import com.FirstSpingApp.demo.domain.Product;

import java.math.BigDecimal;

public class ProductDto {

  private Long id;
  private String name;
  private String brand;
  private String description;
  private BigDecimal price;
  private Long subcategoryId;

  public ProductDto() {}

  public ProductDto(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.brand = product.getBrand();
    this.description = product.getDescription();
    this.price = product.getPrice();
    this.subcategoryId = product.getSubcategory().getId();
  }

  public ProductDto(
      Long id,
      String name,
      String brand,
      String description,
      BigDecimal price,
      Long subcategoryId) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.description = description;
    this.price = price;
    this.subcategoryId = subcategoryId;
  }

  public static Product toEntity(ProductDto dto) {
    // TODO implement me
    return new Product();
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

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Long getSubcategoryId() {
    return subcategoryId;
  }

  public void setSubcategoryId(Long subcategoryId) {
    this.subcategoryId = subcategoryId;
  }
}
