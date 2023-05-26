package com.rest_api.ShopverseBackend.user;


import com.rest_api.ShopverseBackend.utils.JwtUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "https://shopverse-store.netlify.app")
@RequestMapping("api/v1")
@RestController
public class UserController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtUtils jwtUtils;

  @GetMapping("/users")
  public List<User> findAll() {
    System.out.println("we are inside users");
    return userRepository.findAll();
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