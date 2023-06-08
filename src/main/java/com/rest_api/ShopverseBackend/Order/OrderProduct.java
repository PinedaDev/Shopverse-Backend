package com.rest_api.ShopverseBackend.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

  @Column(name = "product_id")
  private UUID productId;

  @Column(name = "color")
  private String color;

  @Column(name = "size")
  private String size;

  @Column(name = "amount")
  private Integer amount;

  // getters and setters
}