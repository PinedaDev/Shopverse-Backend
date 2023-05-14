package com.rest_api.ShopverseBackend.product;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
  @NotBlank
  private String name;

  @Column(nullable = false)
  private String description;

  @Column (nullable = false)
  private String[] categories;


  private LocalDateTime createdAt = LocalDateTime.now();
}
