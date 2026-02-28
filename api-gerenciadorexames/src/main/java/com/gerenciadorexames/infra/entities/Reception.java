package com.gerenciadorexames.infra.entities;

import com.gerenciadorexames.infra.entities.enums.ExamStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reception")
public class Reception {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 15, nullable = false)
  private LocalDate collectionDate;

  @Column(length = 200, nullable = false)
  private String nameRequestDoctor;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ExamStatus status;

  @ManyToOne
  @JoinColumn(name = "patient_id", nullable = false)
  private Patient patient;
}
