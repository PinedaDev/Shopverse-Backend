package com.rest_api.ShopverseBackend.user;


import com.rest_api.ShopverseBackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("api/v1")
@CrossOrigin(origins = "http://127.0.0.1:5173")
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
}