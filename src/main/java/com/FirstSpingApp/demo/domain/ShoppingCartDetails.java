package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart_details")
public class ShoppingCartDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "shopping_cart_details_id_generator")
  @SequenceGenerator(
      name = "shopping_cart_details_id_generator",
      sequenceName = "shopping_cart_details_id_seq",
      allocationSize = 1)
  private Long id;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
  @JsonBackReference(value = "shopping_cart-movement")
  private ShoppingCart shoppingCart;

  @NotNull
  @OneToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product products;

  @NotNull
  @Column(name = "quantity")
  private Integer quantity;

  public ShoppingCartDetails() {}

  public ShoppingCartDetails(
      Long id,
      @NotNull ShoppingCart shoppingCart,
      @NotNull Product products,
      @NotNull Integer quantity) {
    this.id = id;
    this.shoppingCart = shoppingCart;
    this.products = products;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public Product getProducts() {
    return products;
  }

  public void setProducts(Product products) {
    this.products = products;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ShoppingCartDetails)) return false;
    ShoppingCartDetails that = (ShoppingCartDetails) o;
    return Objects.equals(id, that.id)
        && Objects.equals(shoppingCart, that.shoppingCart)
        && Objects.equals(products, that.products)
        && Objects.equals(quantity, that.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, shoppingCart, products, quantity);
  }
}
