package com.FirstSpingApp.demo.repositories;

import com.FirstSpingApp.demo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
