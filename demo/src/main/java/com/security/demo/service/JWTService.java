package com.security.demo.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Decoder;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

  private String secretKey = "";

  public JWTService() {
    try {
      KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = keyGenerator.generateKey();
      secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public String generateToken(String userName) {

    Map<String, Object> claims = new HashMap<>();

    return Jwts
              .builder()
              .claims(claims)
              .subject(userName)
              .issuedAt(new Date())
              .expiration(new Date(new Date().getTime() + 30 * 60 * 1000 ))
              .signWith(getKey())
              .compact();
  }

  private SecretKey getKey() {
    byte[] keyBytes = Base64.getDecoder()
.decode(secretKey.getBytes(StandardCharsets.UTF_8));
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractClaims(token);
    return claimResolver.apply(claims);
  }
  private Claims extractClaims(String token) {
    return Jwts
              .parser()
              .verifyWith(getKey())
              .build()
              .parseSignedClaims(token)
              .getPayload();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpire(token));
  }

  private boolean isTokenExpire(String token) {
    return extractExpiration(token).before(new Date());
  }
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
}
