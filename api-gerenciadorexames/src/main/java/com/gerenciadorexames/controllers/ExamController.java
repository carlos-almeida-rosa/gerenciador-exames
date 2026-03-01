package com.gerenciadorexames.controllers;

import com.gerenciadorexames.infra.entities.Exam;
import com.gerenciadorexames.services.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/exams")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> insertExam(@RequestBody Exam exam){
      Exam newExam = examService.insert(exam);
      return ResponseEntity.status(HttpStatus.CREATED).body(newExam);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> list(){
      return ResponseEntity.ok(examService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> listById(@PathVariable UUID id){
      return ResponseEntity.ok(examService.listById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
      examService.deleteById(id);
      return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateExam(@PathVariable UUID id, @RequestBody Exam exam){
      examService.updateById(id, exam);
      return ResponseEntity.noContent().build();
    }

}
