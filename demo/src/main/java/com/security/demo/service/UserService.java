package com.security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.demo.model.Users;
import com.security.demo.repo.UserRepo;

@Service
public class UserService {

  @Autowired
  private UserRepo repo;

  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
  public Users register(Users user) {
    user.setPassword(encoder.encode(user.getPassword()));
    return repo.save(user);
  }
}
