package com.gerenciadorexames.controllers;

import com.gerenciadorexames.infra.entities.User;
import com.gerenciadorexames.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<User> findUserByEmail (@PathVariable String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }
}
