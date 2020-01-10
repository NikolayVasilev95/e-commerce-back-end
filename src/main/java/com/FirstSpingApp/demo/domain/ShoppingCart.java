package com.FirstSpingApp.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "shopping_cart_id_generator")
  @SequenceGenerator(
      name = "shopping_cart_id_generator",
      sequenceName = "shopping_cart_id_seq",
      allocationSize = 1)
  private Long id;

  @NotNull
  @OneToOne(orphanRemoval = true)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @JsonIgnore
  private User user;

  @OneToMany(
      fetch = FetchType.EAGER,
      mappedBy = "shoppingCart",
      cascade = CascadeType.MERGE,
      orphanRemoval = true)
  @JsonManagedReference(value = "shopping_cart-movement")
  private List<ShoppingCartDetails> shoppingCartDetails = new ArrayList<>();

  public ShoppingCart() {}

  public ShoppingCart(Long id, @NotNull User user, List<ShoppingCartDetails> shoppingCartDetails) {
    this.id = id;
    this.user = user;
    this.shoppingCartDetails = shoppingCartDetails;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<ShoppingCartDetails> getShoppingCartDetails() {
    return shoppingCartDetails;
  }

  public void setShoppingCartDetails(List<ShoppingCartDetails> shoppingCartDetails) {
    this.shoppingCartDetails = shoppingCartDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ShoppingCart)) return false;
    ShoppingCart that = (ShoppingCart) o;
    return Objects.equals(id, that.id)
        && Objects.equals(user, that.user)
        && Objects.equals(shoppingCartDetails, that.shoppingCartDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, shoppingCartDetails);
  }
}
