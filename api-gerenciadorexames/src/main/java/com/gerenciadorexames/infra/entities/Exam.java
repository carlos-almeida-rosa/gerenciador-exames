package com.gerenciadorexames.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String cpf;

    @Column(length = 15, nullable = false)
    private LocalDate data;

    @Column(length = 40, nullable = false)
    private String type;

    @Column(length = 40, nullable = false)
    private String status;

}
