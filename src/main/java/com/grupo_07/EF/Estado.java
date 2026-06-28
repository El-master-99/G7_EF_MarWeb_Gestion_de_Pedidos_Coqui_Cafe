package com.grupo_07.EF;

public enum Estado {
    PENDIENTE("Pendiente"),
    EN_PROGRESO("En Progreso"),
    COMPLETADA("Completada");

    private final String valor;

    Estado(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}