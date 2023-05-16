package com.filiperobot.aluraforumapi.infra.exceptions;

import com.filiperobot.aluraforumapi.infra.exceptions.DTO.DadosErrosValidacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarEntityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErrosValidacao>> tratarErrosDeValidacao(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors()
                .stream()
                .map(DadosErrosValidacao::new)
                .toList();

        return ResponseEntity.badRequest().body(erros);
    }
}
