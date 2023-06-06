package com.rest_api.ShopverseBackend.order;

import com.rest_api.ShopverseBackend.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
  Optional<Order> findById(UUID orderId);
}
