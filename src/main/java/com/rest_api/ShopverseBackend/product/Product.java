package com.rest_api.ShopverseBackend.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "_product")
@Table(name = "_products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
  @Id
  @GeneratedValue
  @JsonProperty("id")
  private UUID productId;

  @Column(nullable = false)
  @NotBlank(message = "Product Name is mandatory")
  private String name;

  @Column(nullable = true)
  private String img;

  @Column(nullable = false, length = 500)
  @NotBlank(message = "Product description is mandatory")
  @Size(min = 20, max=500, message = "Description character range 20 - 500")
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
  @Min(value = 1L, message = "Price must be greater than zero")
  @Max(value = 3_000L, message = "Price mustn't be greater than 3_000")
  private Integer price;

  @Column(nullable = true)
  private int reviews;

  @Column(nullable = true)
  private int stars;

  @CreationTimestamp
  private LocalDateTime createdAt = LocalDateTime.now();
}
