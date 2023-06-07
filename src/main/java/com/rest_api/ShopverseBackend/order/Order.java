package com.rest_api.ShopverseBackend.order;

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
import org.hibernate.annotations.UuidGenerator;

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
  @UuidGenerator
  private UUID orderId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;

  @ElementCollection
  @CollectionTable(name = "_order_product", joinColumns = @JoinColumn(name = "order_id"))
  private List<OrderProduct> products = new ArrayList<>();

  @NotNull
  @ElementCollection
  private List<Integer> quantities = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private Status status;

  private LocalDateTime issuedAt = LocalDateTime.now();

  public Order(User user, List<OrderProduct> products, List<Integer> quantities, Status status) {
    this.user = user;
    this.products = products;
    this.quantities = quantities;
    this.status = status;
  }

  enum Status {
    PENDING,
    TRAVELLING,
    DELIVERED,
  }
}
