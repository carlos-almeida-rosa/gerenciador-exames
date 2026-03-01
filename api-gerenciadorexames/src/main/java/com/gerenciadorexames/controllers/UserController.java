package com.gerenciadorexames.controllers;

import com.gerenciadorexames.infra.entities.User;
import com.gerenciadorexames.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user){
      User newUser = userService.insert(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> list(){
      return ResponseEntity.ok(userService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> listById(@PathVariable UUID id){
      return ResponseEntity.ok(userService.listById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
      userService.deleteById(id);
      return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody User user){
      userService.updateById(id, user);
      return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findUserByEmail (@PathVariable String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }
}
