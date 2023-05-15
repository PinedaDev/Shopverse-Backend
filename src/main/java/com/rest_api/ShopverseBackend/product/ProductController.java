package com.rest_api.ShopverseBackend.product;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController
{
  private final ProductService productService;
  private final ProductRepository productRepo;

  @GetMapping
  public List<Product> findAll()
  {
    return productService.findAll();
  }

  @PostMapping
  public ResponseEntity<Object> saveOne(@RequestBody Product product)
  {
    Optional<Product> existingProduct = productRepo.findByName(product.getName());

    if (existingProduct.isPresent()) {
      return new ResponseEntity<>("Product with same name already exist", HttpStatus.BAD_REQUEST);
    }

    Product newProduct = productService.saveOne(product);

    return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
  }

  @GetMapping("/{productId}")
  public ResponseEntity<Object> findById(@PathVariable UUID productId)
  {
    Optional<Product> product = productService.findById(productId);

    if (product.isEmpty()) {
      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Object> deleteById(@PathVariable UUID productId)
  {
    Optional<Product> product = productRepo.findById(productId);

    if (product.isEmpty()) {
      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    productService.delete(product.get());

    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PatchMapping("/{productId}")
  public ResponseEntity<Object> updateOne(@PathVariable UUID productId, @RequestBody Product product)
  {

    Optional<Product> existingProduct = productRepo.findById(productId);

    if (existingProduct.isEmpty()) {
      return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    if (product.getName() != null) {
      existingProduct.get().setName(product.getName());
    }
    if (product.getDescription() != null) {
      existingProduct.get().setDescription(product.getDescription());
    }

    Product result = productService.updateOne(existingProduct.get());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
  }
