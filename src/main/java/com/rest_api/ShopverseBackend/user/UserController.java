package com.rest_api.ShopverseBackend.user;


import com.rest_api.ShopverseBackend.product.Product;
import com.rest_api.ShopverseBackend.product.ProductService;
import com.rest_api.ShopverseBackend.utils.JwtUtils;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
  private final UserRepository userRepo;
  private final UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtUtils jwtUtils;

  @GetMapping
  public List<User> findAll() {
    return userRepository.findAll();
  }
  @GetMapping("/{userId}")
  public ResponseEntity<Object> findById(@PathVariable UUID userId){
    Optional<User> user = userService.findById(userId);

    if(user.isEmpty()) {
      return new ResponseEntity<>("No user found", HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PatchMapping("/{userId}")
  public ResponseEntity<Object> updateOne(@PathVariable UUID userId, @RequestBody User user) {

    Optional<User> existingUser = userRepository.findById(userId);

    if (existingUser.isEmpty()) {
      return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    User updatedUser = existingUser.get();

    if (user.getUsername() != null) {
      updatedUser.setUsername(user.getUsername());
    }

    User result = userRepository.save(updatedUser);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping("/signin")
  public Map<String, String> login(@RequestBody AuthRequest authRequest) {

    Map<String, String> token = new HashMap<>();
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(),
                    authRequest.getPassword()
            )
    );

    User user = userRepository.findByUsername(authRequest.getUsername());
    token.put("token", jwtUtils.generateToken(user));

    return token;
  }

  @PostMapping("/signup")
  public User signup(@RequestBody User user) {

    User newUser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), User.Role.USER);
    userRepository.save(newUser);

    return newUser;
  }

  @PostConstruct
  public void initAdminUser() {
    String adminUsername = System.getenv("ADMIN_USERNAME");
    String adminPassword = System.getenv("ADMIN_PASSWORD");

    // Check if the admin user already exists
    if (userRepository.findByUsername(adminUsername) == null) {
      // Create the admin user
      User adminUser = new User(adminUsername, passwordEncoder.encode(adminPassword), User.Role.ADMIN);
      userRepository.save(adminUser);
      System.out.println("Admin user initialized successfully.");
    }
  }
}