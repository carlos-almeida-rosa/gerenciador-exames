package com.gerenciadorexames.controllers;

import com.gerenciadorexames.infra.entitys.Exam;
import com.gerenciadorexames.infra.repository.ExamRepository;
import com.gerenciadorexames.services.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> insertExam(@RequestBody Exam exam){
      Exam newExam = examService.insertExam(exam);
      return ResponseEntity.ok(newExam);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> list(){
        return ResponseEntity.ok(examService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> listById(@PathVariable Long id){
      return ResponseEntity.ok(examService.listById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
      examService.deleteById(id);
      return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateExam(@RequestParam Long id, @RequestBody Exam exam){
      examService.updateExamById(id, exam);
      return ResponseEntity.ok().build();
    }

}
