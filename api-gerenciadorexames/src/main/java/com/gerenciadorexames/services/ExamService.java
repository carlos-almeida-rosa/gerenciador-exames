package com.gerenciadorexames.services;

import com.gerenciadorexames.infra.entities.Exam;
import com.gerenciadorexames.infra.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> list(){
        return examRepository.findAll();
    }

    public Exam insertExam(Exam exam){
      return examRepository.saveAndFlush(exam);
    }

    public Exam listById(UUID id){
      return examRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Exame não encontrado!")
      );
    }

    public void deleteById(UUID id){
        examRepository.deleteById(id);
    }

    public void updateExamById(UUID id, Exam exam){
      Exam examEntity = listById(id);
      Exam examUpdated = Exam.builder()
        .id(exam.getId() != null ? exam.getId() : examEntity.getId())
        .name(exam.getName() != null ? exam.getName() : examEntity.getName())
        .measureUnity(exam.getMeasureUnity() != null ? exam.getMeasureUnity() : examEntity.getMeasureUnity())
        .minReference(exam.getMinReference() != null ? exam.getMinReference() : examEntity.getMinReference())
        .maxReference(exam.getMaxReference() != null ? exam.getMaxReference() : examEntity.getMaxReference())
        .build();
      examRepository.saveAndFlush(examUpdated);

    }
}
