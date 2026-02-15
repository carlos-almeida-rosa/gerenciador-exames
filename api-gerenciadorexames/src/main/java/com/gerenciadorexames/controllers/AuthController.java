package com.gerenciadorexames.controllers;

import com.gerenciadorexames.dto.LoginRequestDTO;
import com.gerenciadorexames.dto.RegisterRequestDTO;
import com.gerenciadorexames.dto.ResponseDTO;
import com.gerenciadorexames.infra.entities.User;
import com.gerenciadorexames.infra.repository.UserRepository;
import com.gerenciadorexames.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginRequestDTO body){
    User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    if(this.passwordEncoder.matches(body.password(), user.getPassword())){
      String token = this.tokenService.generateToken(user);
      return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
    }
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegisterRequestDTO body){
    Optional<User> user = this.userRepository.findByEmail(body.email());
    if(user.isEmpty()){
      User newUser = new User();
      newUser.setPassword(this.passwordEncoder.encode(body.password()));
      newUser.setEmail(body.email());
      newUser.setName(body.name());
      this.userRepository.save(newUser);
      String token = this.tokenService.generateToken(newUser);
      return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
    }
    return ResponseEntity.badRequest().build();
  }
}
