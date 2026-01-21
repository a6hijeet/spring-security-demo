package com.security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.demo.model.Users;
import com.security.demo.repo.UserRepo;

@Service
public class UserService {

  @Autowired
  private UserRepo repo;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JWTService jwtService;

  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
  public Users register(Users user) {
    user.setPassword(encoder.encode(user.getPassword()));
    return repo.save(user);
  }

  public String verify(Users user) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
    );

    return authentication.isAuthenticated() ? jwtService.generateToken(user.getUsername()): "";
  }
}
