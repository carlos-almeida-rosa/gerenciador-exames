package com.gerenciadorexames.infra.repository;

import com.gerenciadorexames.infra.entities.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<ExamResult, UUID> {
}
