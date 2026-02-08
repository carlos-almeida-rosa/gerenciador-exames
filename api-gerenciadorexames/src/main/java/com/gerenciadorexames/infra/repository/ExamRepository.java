package com.gerenciadorexames.infra.repository;

import com.gerenciadorexames.infra.entitys.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
  Optional<Exam> findById(Long id);
}
