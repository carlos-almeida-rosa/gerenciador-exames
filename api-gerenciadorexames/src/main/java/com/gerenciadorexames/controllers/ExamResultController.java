package com.gerenciadorexames.controllers;

import com.gerenciadorexames.infra.entities.ExamResult;
import com.gerenciadorexames.services.ExamResultService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/examresult")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class ExamResultController {

  private final ExamResultService examResultService;

  @PostMapping
  public ResponseEntity<ExamResult> insertExamResult(@RequestBody ExamResult examResult){
    ExamResult newExamResult = examResultService.insert(examResult);
    return ResponseEntity.status(HttpStatus.CREATED).body(newExamResult);
  }

  @GetMapping
  public ResponseEntity<Page<ExamResult>> listPage(Pageable pageable){
    return ResponseEntity.ok(examResultService.listPage(pageable));
  }

  @GetMapping
  public ResponseEntity<List<ExamResult>> list(){
    return ResponseEntity.ok(examResultService.list());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExamResult> listById(@PathVariable UUID id){
    return ResponseEntity.ok(examResultService.listById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id){
    examResultService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateExamResult(@PathVariable UUID id, @RequestBody ExamResult examResult){
    examResultService.updateById(id, examResult);
    return ResponseEntity.noContent().build();
  }

}
