package com.security.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
  
  @GetMapping("/")
  public String greetUser(HttpServletRequest request) {
      return "Hello Guest " + request.getSession().getId();
  }
  
}
