package com.filiperobot.aluraforumapi.domain.course;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "cursos")
@Entity(name = "curso")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    public Curso(DadosCurso dadosCurso) {
        this.nome = dadosCurso.nome();
        this.categoria = dadosCurso.categoria();
    }

    public void atualizar(DadosCursoAtualizar dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
    }
}
