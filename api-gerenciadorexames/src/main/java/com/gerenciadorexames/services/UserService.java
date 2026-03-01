package com.gerenciadorexames.services;

import com.gerenciadorexames.infra.entities.User;
import com.gerenciadorexames.infra.entities.enums.Role;
import com.gerenciadorexames.infra.repository.UserRepository;
import com.gerenciadorexames.services.interfaces.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements CRUDService<User, UUID> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail (String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado!")
        );
    }

    @Override
    public List<User> list() {
      return userRepository.findAll();
    }

    @Override
    public User insert(User entity) {
      return userRepository.saveAndFlush(entity);
    }

    @Override
    public User listById(UUID id) {
      return userRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Usuário não encontrado!")
      );
    }

    @Override
    public void deleteById(UUID id) {
      userRepository.deleteById(id);
    }

    @Override
    public void updateById(UUID id, User entity) {
        User userEntity = listById(id);
        User userUpdated = User.builder()
          .id(entity.getId() != null ? entity.getId() : userEntity.getId())
          .name(entity.getName() != null ? entity.getName() : userEntity.getName())
          .email(entity.getEmail() != null ? entity.getEmail() : userEntity.getEmail())
          .password(entity.getPassword() != null ? entity.getPassword() : userEntity.getPassword())
          .role(entity.getRole() != null ? entity.getRole() : userEntity.getRole())
          .build();
        userRepository.save(userUpdated);
    }
}
