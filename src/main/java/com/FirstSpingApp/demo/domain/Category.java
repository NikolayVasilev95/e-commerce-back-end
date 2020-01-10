package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "category_id_generator")
  @SequenceGenerator(
      name = "category_id_generator",
      sequenceName = "category_id_seq",
      allocationSize = 1)
  private Long id;

  @NotBlank
  @Column(name = "name")
  @Size(max = 25)
  private String name;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "category",
      cascade = CascadeType.MERGE,
      orphanRemoval = true)
  @JsonManagedReference(value = "category-movement")
  private List<Subcategory> subcategories = new ArrayList<>();

  public Category() {}

  public Category(Long id, @NotBlank @Size(max = 25) String name, List<Subcategory> subcategories) {
    this.id = id;
    this.name = name;
    this.subcategories = subcategories;
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

  public List<Subcategory> getSubcategories() {
    return subcategories;
  }

  public void setSubcategories(List<Subcategory> subcategories) {
    this.subcategories = subcategories;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Category)) return false;
    Category category = (Category) o;
    return Objects.equals(id, category.id)
        && Objects.equals(name, category.name)
        && Objects.equals(subcategories, category.subcategories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, subcategories);
  }
}
