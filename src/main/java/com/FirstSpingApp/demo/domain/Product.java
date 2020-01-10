package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_id_generator")
  @SequenceGenerator(
      name = "product_id_generator",
      sequenceName = "product_id_seq",
      allocationSize = 1)
  private Long id;

  @NotBlank
  @Column(name = "name")
  private String name;

  @NotBlank
  @Column(name = "brand")
  @Size(max = 25)
  private String brand;

  @NotBlank
  @Column(name = "description")
  @Size(max = 255)
  private String description;

  @NotNull
  @Column(name = "price")
  private BigDecimal price;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "image_id", nullable = false)
  @JsonBackReference(value = "image-movement")
  private Image image;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "subcategory_id", nullable = false)
  @JsonBackReference(value = "subcategory-movement")
  private Subcategory subcategory;

  public Product() {}

  public Product(
      Long id,
      @NotBlank String name,
      @NotBlank @Size(max = 25) String brand,
      @NotBlank @Size(max = 255) String description,
      @NotNull BigDecimal price,
      Image image,
      Subcategory subcategory) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.description = description;
    this.price = price;
    this.image = image;
    this.subcategory = subcategory;
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

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public Subcategory getSubcategory() {
    return subcategory;
  }

  public void setSubcategory(Subcategory subcategory) {
    this.subcategory = subcategory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Product)) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id)
        && Objects.equals(name, product.name)
        && Objects.equals(brand, product.brand)
        && Objects.equals(description, product.description)
        && Objects.equals(price, product.price)
        && Objects.equals(image, product.image)
        && Objects.equals(subcategory, product.subcategory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, brand, description, price, image, subcategory);
  }
}
