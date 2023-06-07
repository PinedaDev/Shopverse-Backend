package com.rest_api.ShopverseBackend.user;

import com.rest_api.ShopverseBackend.order.Order;
import com.rest_api.ShopverseBackend.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepo;

  public List<User> findAll() { return userRepo.findAll();}

  public Optional<User> findById(UUID userId) { return userRepo.findById(userId);}

  public User saveOne(User user) {return userRepo.save(user);}

  public User updateOne(User user) { return userRepo.save(user);}
}
