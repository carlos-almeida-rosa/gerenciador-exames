package com.gerenciadorexames.controllers;

import com.gerenciadorexames.infra.entities.Reception;
import com.gerenciadorexames.services.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reception")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class ReceptionController {

  private final ReceptionService receptionService;

  @PostMapping
  public ResponseEntity<Reception> insertReception(@RequestBody Reception reception){
    Reception newReception = receptionService.insert(reception);
    return ResponseEntity.status(HttpStatus.CREATED).body(newReception);
  }

  @GetMapping
  public ResponseEntity<List<Reception>> list(){
    return ResponseEntity.ok(receptionService.list());
  }

  @GetMapping
  public ResponseEntity<Page<Reception>> listPage(Pageable pageable){
    return ResponseEntity.ok(receptionService.listPage(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Reception> listById(@PathVariable UUID id){
    return ResponseEntity.ok(receptionService.listById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id){
    receptionService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateReception(@PathVariable UUID id, @RequestBody Reception reception){
    receptionService.updateById(id, reception);
    return ResponseEntity.noContent().build();
  }

}
