package com.FirstSpingApp.demo.services.impl;

import com.FirstSpingApp.demo.domain.*;
import com.FirstSpingApp.demo.exceptionhandling.exception.ResourceNotFoundException;
import com.FirstSpingApp.demo.repositories.OrderRepository;
import com.FirstSpingApp.demo.repositories.ProductRepository;
import com.FirstSpingApp.demo.resources.OrderPostDto;
import com.FirstSpingApp.demo.resources.ProductOrderPostDTO;
import com.FirstSpingApp.demo.services.OrderService;
import com.FirstSpingApp.demo.services.ShoppingCartService;
import com.FirstSpingApp.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired private OrderRepository orderRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private UserService userService;

  @Autowired private ShoppingCartService shoppingCartService;

  @Override
  public Iterable<Order> getAllOrders() {
    return this.orderRepository.findAll();
  }

  @Override
  @Transactional
  public Order create(OrderPostDto order) {
    List<Product> products =
        productRepository.findAllById(
            order.getProducts().stream()
                .map(ProductOrderPostDTO::getProductId)
                .collect(Collectors.toList()));
    User user = userService.findById(order.getUserId());
    if (products.size() != order.getProducts().size()) {
      throw new ResourceNotFoundException("Some of the products are not found");
    }
    Order newOrder = new Order();
    newOrder.setUser(user);
    newOrder.setDateCreated(LocalDate.now());
    newOrder.setStatus("ACCEPTED");
    newOrder = orderRepository.save(newOrder);
    for (ProductOrderPostDTO dto : order.getProducts()) {
      newOrder
          .getOrderProducts()
          .add(
              new OrderProduct(
                  new OrderProductPK(newOrder.getId(), dto.getProductId()),
                  newOrder,
                  productRepository.getOne(dto.getProductId()),
                  dto.getQuantity()));
    }
    shoppingCartService.clear(shoppingCartService.getShoppingCart(user.getId()).getId());
    return this.orderRepository.save(newOrder);
  }

  @Override
  public void update(Order order) {
    this.orderRepository.save(order);
  }

  @Override
  public void delete(Long id) {
    orderRepository.deleteById(id);
  }
}
