package com.gerenciadorexames.infra.repository;

import com.gerenciadorexames.infra.entities.Reception;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReceptionRepository extends JpaRepository<Reception, UUID> {
  Optional<Reception> findById(UUID uuid);
}
