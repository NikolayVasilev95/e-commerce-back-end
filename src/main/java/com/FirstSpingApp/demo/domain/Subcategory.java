package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subcategories")
public class Subcategory implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "subcategory_id_generator")
  @SequenceGenerator(
      name = "subcategory_id_generator",
      sequenceName = "subcategory_id_seq",
      allocationSize = 1)
  private Long id;

  @NotBlank
  @Column(name = "name")
  @Size(max = 25)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @JoinColumn(name = "category_id", nullable = false)
  @JsonBackReference(value = "category-movement")
  private Category category;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "subcategory",
      cascade = CascadeType.MERGE,
      orphanRemoval = true)
  @JsonManagedReference(value = "subcategory-movement")
  private List<Product> products = new ArrayList<>();

  public Subcategory() {}

  public Subcategory(
      Long id, @NotBlank @Size(max = 25) String name, Category category, List<Product> products) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.products = products;
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

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Subcategory)) return false;
    Subcategory that = (Subcategory) o;
    return Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(category, that.category)
        && Objects.equals(products, that.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, category, products);
  }
}
