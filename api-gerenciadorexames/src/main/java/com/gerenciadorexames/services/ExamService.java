package com.gerenciadorexames.services;

import com.gerenciadorexames.infra.entities.Exam;
import com.gerenciadorexames.infra.repository.ExamRepository;
import com.gerenciadorexames.services.interfaces.CRUDService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ExamService implements CRUDService<Exam, UUID> {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public List<Exam> list(){
        return examRepository.findAll();
    }

    @Override
    public Exam insert(Exam entity){
      return examRepository.saveAndFlush(entity);
    }

    @Override
    public Exam listById(UUID id){
      return examRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Exame não encontrado!")
      );
    }

    @Override
    public void deleteById(UUID id){
      examRepository.deleteById(id);
    }

    @Override
    public void updateById(UUID id, Exam entity) {
      Exam examEntity = listById(id);
      Exam examUpdated = Exam.builder()
        .id(entity.getId() != null ? entity.getId() : examEntity.getId())
        .name(entity.getName() != null ? entity.getName() : examEntity.getName())
        .measureUnity(entity.getMeasureUnity() != null ? entity.getMeasureUnity() : examEntity.getMeasureUnity())
        .minReference(entity.getMinReference() != null ? entity.getMinReference() : examEntity.getMinReference())
        .maxReference(entity.getMaxReference() != null ? entity.getMaxReference() : examEntity.getMaxReference())
        .build();
      examRepository.saveAndFlush(examUpdated);
    }
}
