package com.gerenciadorexames.services;

import com.gerenciadorexames.infra.entities.User;
import com.gerenciadorexames.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail (String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado!")
        );
    }
}
