package com.gerenciadorexames.services;

import com.gerenciadorexames.infra.entities.ExamResult;
import com.gerenciadorexames.infra.repository.ExamResultRepository;
import com.gerenciadorexames.services.interfaces.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExamResultService implements CRUDService<ExamResult, UUID> {

    private final ExamResultRepository examResultRepository;

    public ExamResultService(ExamResultRepository examResultRepository) {
      this.examResultRepository = examResultRepository;
    }

    @Override
    public List<ExamResult> list() {
      return examResultRepository.findAll();
    }

    @Override
    public ExamResult insert(ExamResult entity) {
      return examResultRepository.saveAndFlush(entity);
    }

    @Override
    public ExamResult listById(UUID id) {
      return examResultRepository.findById(id).orElseThrow(
        () ->  new RuntimeException("Resultado do exame não encontrado!")
      );
    }

    @Override
    public void deleteById(UUID id) {
      examResultRepository.deleteById(id);
    }

    @Override
    public void updateById(UUID id, ExamResult entity) {
      ExamResult examResultEntity = listById(id);
      ExamResult examResultUpdated = ExamResult.builder()
        .id(entity.getId() != null ? entity.getId() : examResultEntity.getId())
        .reception(entity.getReception() != null ? entity.getReception() : examResultEntity.getReception())
        .exam(entity.getExam() != null ? entity.getExam() : examResultEntity.getExam())
        .obtainedValue(entity.getObtainedValue() != null ? entity.getObtainedValue() : examResultEntity.getObtainedValue())
        .flagChanged(entity.isFlagChanged())
        .build();
      examResultRepository.save(examResultUpdated);
    }
}
