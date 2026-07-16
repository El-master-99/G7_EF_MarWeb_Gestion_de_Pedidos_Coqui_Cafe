package com.grupo07.coquicafe.model;

public enum Estado {
    PENDIENTE("Pendiente"),
    EN_PROGRESO("En Progreso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada");

    private final String valor;

    Estado(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}