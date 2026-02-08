package com.gerenciadorexames.infra.entitys.enums;

public enum ExamType {
    SANGUE("Sangue"),
    RAIO_X("Raio X"),
    ULTRASSONOGRAFIA("Ultrassonografia"),
    RESSONANCIA("Ressonância"),
    URINA("Urina"),
    TOMOGRAFIA("Tomografia");

    private String descricao;

    ExamType(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
