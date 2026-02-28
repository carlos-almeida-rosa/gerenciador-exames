package com.gerenciadorexames.infra.entities;

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
@Table(name = "patients")
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(length = 200, nullable = false)
  private String name;

  @Column(length = 20, nullable = false)
  private String cpf;

  @Column(length = 15, nullable = false)
  private LocalDate birthDate;

  @Column(length = 15, nullable = false)
  private String sex;

  @Column(length = 25, nullable = false)
  private String phone;

  @Column(length = 200, nullable = false)
  private String email;
}
