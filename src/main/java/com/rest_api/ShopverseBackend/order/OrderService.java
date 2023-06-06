package com.rest_api.ShopverseBackend.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
  private final OrderRepository orderRepo;

  public List<Order> findAll() {
    return orderRepo.findAll();
  }

  public Order saveOne(Order order) { return orderRepo.save(order);}

  public Optional<Order> findById(UUID orderId) { return orderRepo.findById(orderId);}

  public void delete(Order order) { orderRepo.delete(order);}

  public Order updateOne(Order order) { return orderRepo.save(order);}
}
