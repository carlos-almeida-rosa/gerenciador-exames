package com.gerenciadorexames.services;

import com.gerenciadorexames.infra.entities.Reception;
import com.gerenciadorexames.infra.repository.ReceptionRepository;
import com.gerenciadorexames.services.interfaces.CRUDService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.UUID;


@Service
public class ReceptionService implements CRUDService<Reception, UUID> {

    private final ReceptionRepository receptionRepository;

    public ReceptionService(ReceptionRepository receptionRepository) {
      this.receptionRepository = receptionRepository;
    }

    @Override
    public List<Reception> list(){
      return receptionRepository.findAll();
    }

    public Page<Reception> listPage(Pageable pageable){
      return receptionRepository.findAll(pageable);
    }

    @Override
    public Reception insert(Reception entity){
      return receptionRepository.saveAndFlush(entity);
    }

    @Override
    public Reception listById(UUID id){
      return receptionRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Atendimento não encontrado!")
      );
    }

    @Override
    public void deleteById(UUID id){
      receptionRepository.deleteById(id);
    }

    @Override
    public void updateById(UUID id, Reception entity) {
      Reception receptionEntity = listById(id);
      Reception receptionUpdated = Reception.builder()
        .id(entity.getId() != null ? entity.getId() : receptionEntity.getId())
        .patient(entity.getPatient() != null ? entity.getPatient() : receptionEntity.getPatient())
        .collectionDate(entity.getCollectionDate() != null ? entity.getCollectionDate() : receptionEntity.getCollectionDate())
        .nameRequestDoctor(entity.getNameRequestDoctor() != null ? entity.getNameRequestDoctor() : receptionEntity.getNameRequestDoctor())
        .status(entity.getStatus() != null ? entity.getStatus() : receptionEntity.getStatus())
        .build();
      receptionRepository.save(receptionUpdated);
    }
}
