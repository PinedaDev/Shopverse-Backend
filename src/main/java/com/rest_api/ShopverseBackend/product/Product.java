package com.rest_api.ShopverseBackend.product;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Entity(name = "product")
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
  @Id
  @GeneratedValue
  private UUID productId;

  @Column(nullable = false)
  @NotBlank(message = "Product Name is mandatory")
  private String name;

  @Column(nullable = true)
  private String img;

  @Column(nullable = false)
  @NotBlank(message = "Product description is mandatory")
  private String description;

  @Column(nullable = false)
  @NotNull(message = "Provide at least one category")
  private String[] categories;

  @Column(nullable = false)
  @NotEmpty(message = "Provide at least one color variant")
  private String[] colors;

  @Column(nullable = false)
  @NotEmpty(message = "Provide at least one size variant")
  private int[] sizes;

  @Column(nullable = false)
  @NotNull
  private int price;

  @Column(nullable = true)
  private int reviews;

  @Column(nullable = true)
  private int stars;

  private LocalDateTime createdAt = LocalDateTime.now();
}
