package com.gerenciadorexames.services;

import com.gerenciadorexames.infra.entitys.Exam;
import com.gerenciadorexames.infra.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> list(){
        return examRepository.findAll();
    }

    public void insertExam(Exam exam){
      examRepository.saveAndFlush(exam);
    }

    public Exam listById(Long id){
      return examRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Exame não encontrado!")
      );
    }

    public void deleteById(Long id){
        examRepository.deleteById(id);
    }

    public void updateExamById(Long id, Exam exam){
      Exam examEntity = listById(id);
      Exam examUpdated = Exam.builder()
        .id(exam.getId() != null ? exam.getId() : examEntity.getId())
        .name(exam.getName() != null ? exam.getName() : examEntity.getName())
        .cpf(exam.getCpf() != null ? exam.getCpf() : examEntity.getCpf())
        .data(exam.getData() != null ? exam.getData() : examEntity.getData())
        .status(exam.getStatus() != null ? exam.getStatus() : examEntity.getStatus())
        .type(exam.getType() != null ? exam.getType() : examEntity.getType())
        .build();
      examRepository.saveAndFlush(examUpdated);

    }
}
