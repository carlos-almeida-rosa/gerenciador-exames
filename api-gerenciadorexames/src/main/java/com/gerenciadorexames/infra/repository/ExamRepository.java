package com.gerenciadorexames.infra.repository;

import com.gerenciadorexames.infra.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExamRepository extends JpaRepository<Exam, UUID> {
  Optional<Exam> findById(Long id);
}
