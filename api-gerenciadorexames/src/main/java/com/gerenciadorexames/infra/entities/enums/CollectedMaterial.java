package com.gerenciadorexames.infra.entities.enums;

public enum CollectedMaterial {
  SANGUE("Sangue"),
  URINA("Urina"),
  FEZES("Fezes"),
  SECRECAO("Secreção");

  private String descricao;

  CollectedMaterial(String descricao){
    this.descricao = descricao;
  }

  public String getDescricao(){
    return descricao;
  }
}
