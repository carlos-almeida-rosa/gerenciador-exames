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
}
