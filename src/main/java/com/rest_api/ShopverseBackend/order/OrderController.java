package com.rest_api.ShopverseBackend.order;

import com.rest_api.ShopverseBackend.product.Product;
import com.rest_api.ShopverseBackend.product.ProductRepository;
import com.rest_api.ShopverseBackend.user.User;
import com.rest_api.ShopverseBackend.user.UserRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
  private final OrderService orderService;
  private final ProductRepository productRepo;
  private final OrderRepository orderRepo;
  private final UserRepository userRepo;

  @GetMapping
  public List<Order> findAll() {
    return orderService.findAll();
  }

  @PostMapping
  public ResponseEntity<Object> saveOne(@RequestBody Order orderRequest) {
    Optional<User> existingUser = userRepo.findById(orderRequest.getUser().getId());

    if (existingUser.isEmpty()) {
      return new ResponseEntity<>("No user found to make order", HttpStatus.BAD_REQUEST);
    }

    // Update the Order entity with the productIds and quantities
    List<OrderProduct> orderProducts = orderRequest.getProducts();
    List<UUID> productIds = orderProducts.stream()
            .map(OrderProduct::getId)
            .toList();

    List<Product> existingProducts = productRepo.findAllById(productIds);
    if (existingProducts.size() != productIds.size()) {
      return new ResponseEntity<>("Some products do not exist", HttpStatus.BAD_REQUEST);
    }
    // Generate the UUID manually
    UUID orderId = UUID.randomUUID();

    // Update the Order entity with the generated orderId
    Order newOrder = new Order(existingUser.get(), orderRequest.getProducts(), orderRequest.getQuantities(), Order.Status.PENDING);
    newOrder.setOrderId(orderId);

    orderRepo.save(newOrder);
    return new ResponseEntity<>(newOrder, HttpStatus.OK);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getConstraintViolations().forEach(constraintViolation -> {
      String fieldName = constraintViolation.getPropertyPath().toString();
      String errorMessage = constraintViolation.getMessage();
      errors.put(fieldName, errorMessage);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}

