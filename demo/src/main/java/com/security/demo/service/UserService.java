package com.security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.demo.model.Users;
import com.security.demo.repo.UserRepo;

@Service
public class UserService {

  @Autowired
  private UserRepo repo;

  public Users register(Users user) {
    return repo.save(user);
  }
}
