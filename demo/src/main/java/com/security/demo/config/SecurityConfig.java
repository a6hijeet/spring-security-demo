package com.security.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.security.demo.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Autowired
  private CustomUserDetailService userDetailService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    // Disable CSRF
    return http.csrf(customizer -> customizer.disable())
        // Add authentication
        .authorizeHttpRequests(request -> request.anyRequest().authenticated())
        // Add form login for browser
        // disabling form login to enable stateless session
        // This will add http authentication
        /* .formLogin(Customizer.withDefaults()) */
        // enable form login for api eg. postman
        .httpBasic(Customizer.withDefaults())
        // Making each request stateless
        // it will generate new session id for each request.
        .sessionManagement(session 
          -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

 /*  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user1 = User.withDefaultPasswordEncoder()
                            .username("navin")
                            .password("n@123")
                            .roles("USER")
                            .build();
    UserDetails user2 = User.withDefaultPasswordEncoder()
                            .username("harsh")
                            .password("h@123")
                            .roles("ADMIN")
                            .build();
    return new InMemoryUserDetailsManager(user1, user2);
  } */

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailService);
    provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    return provider;
  }
}
