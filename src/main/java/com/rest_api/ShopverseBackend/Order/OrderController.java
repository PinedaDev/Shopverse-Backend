package com.rest_api.ShopverseBackend.Order;

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

  //fix me
  /*
  @GetMapping("/user/{userId}")
  public ResponseEntity<Object> findOrdersByUserId(@PathVariable UUID userId) {
    Optional<List<Order>> orders = orderService.findByUserId(userId);

    if (orders.isEmpty()) {
      return new ResponseEntity<>("No orders found for the user", HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

   */

  @PostMapping
  public ResponseEntity<Object> saveOne(@RequestBody Order orderRequest) {
    Optional<User> existingUser = userRepo.findById(orderRequest.getUser().getId());

    if (existingUser.isEmpty()) {
      return new ResponseEntity<>("No user found to make order", HttpStatus.BAD_REQUEST);
    }

    // Validate the products list
    List<OrderProduct> orderProductRequests = orderRequest.getOrderProducts();

    Set<String> uniqueCombinations = new HashSet<>();
    for (OrderProduct orderProductRequest : orderProductRequests) {
      String combination = orderProductRequest.getProductId() + "-" +
              orderProductRequest.getColor() + "-" +
              orderProductRequest.getSize();

      if (!uniqueCombinations.add(combination)) {
        return new ResponseEntity<>("Duplicate product combination found", HttpStatus.BAD_REQUEST);
      }
    }

    List<UUID> productIds = orderProductRequests.stream()
            .map(OrderProduct::getProductId)
            .collect(Collectors.toList());

    List<Product> existingProducts = productRepo.findAllById(productIds);

    Set<UUID> existingProductIds = existingProducts.stream()
            .map(Product::getProductId)
            .collect(Collectors.toSet());

    if (!existingProductIds.containsAll(productIds)) {
      return new ResponseEntity<>("Some products do not exist", HttpStatus.BAD_REQUEST);
    }


    // Create the Order entity
    Order newOrder = new Order();
    newOrder.setUser(existingUser.get());

    List<OrderProduct> orderProducts = new ArrayList<>();
    for (OrderProduct orderProductRequest : orderProductRequests) {
      OrderProduct orderProduct = new OrderProduct();
      orderProduct.setProductId(orderProductRequest.getProductId());
      orderProduct.setColor(orderProductRequest.getColor());
      orderProduct.setSize(orderProductRequest.getSize());
      orderProduct.setAmount(orderProductRequest.getAmount());
      orderProducts.add(orderProduct);
    }

    // Set the orderProducts directly to the Order entity
    newOrder.setOrderProducts(orderProducts);

    // Generate the UUID manually
    UUID orderId = UUID.randomUUID();
    newOrder.setOrderId(orderId);
    newOrder.setStatus(Order.Status.PENDING);

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