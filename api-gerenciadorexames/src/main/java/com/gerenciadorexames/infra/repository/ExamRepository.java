package com.gerenciadorexames.infra.repository;

import com.gerenciadorexames.infra.entitys.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
