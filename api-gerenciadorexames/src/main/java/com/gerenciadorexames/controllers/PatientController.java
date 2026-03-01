package com.gerenciadorexames.controllers;

import com.gerenciadorexames.infra.entities.ExamResult;
import com.gerenciadorexames.infra.entities.Patient;
import com.gerenciadorexames.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class PatientController {

  private final PatientService patientService;

  @PostMapping
  public ResponseEntity<Patient> insertPatient(@RequestBody Patient patient){
    Patient newPatient = patientService.insert(patient);
    return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
  }

  @GetMapping
  public ResponseEntity<List<Patient>> list(){
    return ResponseEntity.ok(patientService.list());
  }

  @GetMapping
  public ResponseEntity<Page<Patient>> listPage(Pageable pageable){
    return ResponseEntity.ok(patientService.listPage(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Patient> listById(@PathVariable UUID id){
    return ResponseEntity.ok(patientService.listById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id){
    patientService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updatePatient(@PathVariable UUID id, @RequestBody Patient patient){
    patientService.updateById(id, patient);
    return ResponseEntity.noContent().build();
  }

}
