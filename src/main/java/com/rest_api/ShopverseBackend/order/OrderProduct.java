package com.rest_api.ShopverseBackend.order;

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

  private UUID productId;
  private String color;
  private String size;
  private Integer amount;
}
