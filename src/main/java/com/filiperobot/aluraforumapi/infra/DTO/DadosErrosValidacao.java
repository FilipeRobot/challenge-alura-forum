package com.filiperobot.aluraforumapi.infra.DTO;

import org.springframework.validation.FieldError;

public record DadosErrosValidacao(String campo, String mensagem) {
    public DadosErrosValidacao(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
