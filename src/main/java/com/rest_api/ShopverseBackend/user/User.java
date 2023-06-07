package com.rest_api.ShopverseBackend.user;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest_api.ShopverseBackend.order.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "_user")
@Table(name = "_users")
@Data
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  @Column(unique = true)
  private String username;

  @Enumerated(EnumType.STRING)
  private Role role;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Order> orders = new ArrayList<>();

  public User(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  enum Role {
    USER,
    ADMIN
  }
}