package com.rest_api.ShopverseBackend.SecurityConfig;


import com.rest_api.ShopverseBackend.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private JwtFilter jwtFilter;

  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/signup", "/api/v1/signin")
            .permitAll()
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/api/v1/products")
            .permitAll()
            .requestMatchers("/api/v1/users").hasRole("ADMIN")
            .requestMatchers("/api/v1/products").hasRole("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // Add JWT token filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}