package com.gerenciadorexames.controllers;

import com.gerenciadorexames.infra.entitys.Exam;
import com.gerenciadorexames.infra.repository.ExamRepository;
import com.gerenciadorexames.services.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public List<Exam> list(){
        return examService.list();
    }
}
