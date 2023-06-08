package com.rest_api.ShopverseBackend.Order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
  private final OrderRepository orderRepo;

  public List<Order> findAll() { return orderRepo.findAll(); }

  public Order saveOne(Order order) { return orderRepo.save(order); }

  public Optional<Order> findById(UUID orderId) { return orderRepo.findById(orderId);}
}
