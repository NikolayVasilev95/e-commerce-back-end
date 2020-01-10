package com.FirstSpingApp.demo.services;

import com.FirstSpingApp.demo.domain.Order;
import com.FirstSpingApp.demo.resources.OrderPostDto;

public interface OrderService {

  Iterable<Order> getAllOrders();

  Order create(OrderPostDto order);

  void update(Order order);

  void delete(Long id);
}
