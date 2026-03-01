package com.gerenciadorexames.services;

import com.gerenciadorexames.infra.entities.Patient;
import com.gerenciadorexames.infra.repository.PatientRepository;
import com.gerenciadorexames.services.interfaces.CRUDService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService implements CRUDService<Patient, UUID> {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
      this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> list(){
      return patientRepository.findAll();
    }

    @Override
    public Patient insert(Patient entity){
      return patientRepository.saveAndFlush(entity);
    }

    @Override
    public Patient listById(UUID id){
      return patientRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Paciente não encontrado")
      );
    }

    @Override
    public void deleteById(UUID id) {
      patientRepository.deleteById(id);
    }

    @Override
    public void updateById(UUID id, Patient entity){
      Patient patientEntity = listById(id);
      Patient patientUpdated = Patient.builder()
        .id(entity.getId() != null ? entity.getId() : patientEntity.getId())
        .name(entity.getName() != null ? entity.getName() : patientEntity.getName())
        .cpf(entity.getCpf() != null ? entity.getCpf() : patientEntity.getCpf())
        .birthDate(entity.getBirthDate() != null ? entity.getBirthDate() : patientEntity.getBirthDate())
        .sex(entity.getSex() != null ? entity.getSex() : patientEntity.getSex())
        .phone(entity.getPhone() != null ? entity.getPhone() : patientEntity.getPhone())
        .email(entity.getEmail() != null ? entity.getEmail() : patientEntity.getEmail())
        .build();
      patientRepository.saveAndFlush(patientUpdated);
    }

}
