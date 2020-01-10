package com.FirstSpingApp.demo.controllers;

import com.FirstSpingApp.demo.domain.Order;
import com.FirstSpingApp.demo.resources.OrderDto;
import com.FirstSpingApp.demo.resources.OrderPostDto;
import com.FirstSpingApp.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@Validated
public class OrderController {

  @Autowired OrderService orderService;

  @GetMapping("/api/admin/orders")
  public @NotNull List<OrderDto> showProducts() {
    return StreamSupport.stream(orderService.getAllOrders().spliterator(), false)
        .map(OrderDto::new)
        .collect(Collectors.toList());
  }

  @PostMapping(value = "/api/user/order/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addOrder(@RequestBody @Valid OrderPostDto orderPostDTO) {
    orderService.create(orderPostDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping(value = "/api/admin/order/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity deleteOrder(@RequestParam(value = "id", required = true) Long id) {
    orderService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
