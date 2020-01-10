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
@Table(name = "images")
public class Image implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "image_id_generator")
  @SequenceGenerator(name = "image_id_generator", sequenceName = "image_id_seq", allocationSize = 1)
  private Long id;

  @NotBlank
  @Column(name = "public_path")
  @Size(max = 255)
  private String publicPath;

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "image",
      cascade = CascadeType.MERGE,
      orphanRemoval = true)
  @JsonManagedReference(value = "image-movement")
  private List<Product> products = new ArrayList<>();

  public Image() {}

  public Image(Long id, @NotBlank @Size(max = 255) String publicPath, List<Product> products) {
    this.id = id;
    this.publicPath = publicPath;
    this.products = products;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPublicPath() {
    return publicPath;
  }

  public void setPublicPath(String publicPath) {
    this.publicPath = publicPath;
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
    if (!(o instanceof Image)) return false;
    Image image = (Image) o;
    return Objects.equals(id, image.id)
        && Objects.equals(publicPath, image.publicPath)
        && Objects.equals(products, image.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, publicPath, products);
  }
}
