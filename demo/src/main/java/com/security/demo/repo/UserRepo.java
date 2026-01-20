package com.security.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.demo.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

 /*  public Users loadUserByUsername(String username); */
 Users findByUsername(String username);

}
