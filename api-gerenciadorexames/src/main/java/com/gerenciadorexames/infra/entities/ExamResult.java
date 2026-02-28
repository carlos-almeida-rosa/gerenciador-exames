package com.gerenciadorexames.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "examresult")
public class ExamResult {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = true)
  private Double obtainedValue;

  private boolean flagChanged;

  @ManyToOne
  @JoinColumn(name = "reception_id", nullable = false)
  private Reception reception;

  @ManyToOne
  @JoinColumn(name = "exam_id", nullable = false)
  private Exam exam;

}
