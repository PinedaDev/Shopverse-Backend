package com.rest_api.ShopverseBackend.Order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest_api.ShopverseBackend.product.Product;
import com.rest_api.ShopverseBackend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "_order")
@Table(name = "_orders")
@Data
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue
  private UUID orderId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;

  @ElementCollection
  @CollectionTable(name = "_order_product", joinColumns = @JoinColumn(name = "order_id"))
  @Column(name = "product_id")
  private List<OrderProduct> orderProducts = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private Status status;

  private LocalDateTime issuedAt = LocalDateTime.now();

  public Order(User user, List<OrderProduct> orderProducts, Status status) {
    this.user = user;
    this.orderProducts = orderProducts;
    this.status = status;
  }

  enum Status {
    PENDING,
    TRAVELLING,
    DELIVERED,
  }
}