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
    @Column(nullable = false, name = "nome")
    private String nome;
    @Column(nullable = false, name = "categoria")
    private String categoria;

    public Curso(DadosCurso dadosCurso) {
        this.nome = dadosCurso.nome();
        this.categoria = dadosCurso.categoria();
    }

    public void atualizar(DadosCursoAtualizar dadosCurso) {
        if (dadosCurso.nome() != null) {
            this.nome = dadosCurso.nome();
        }
        if (dadosCurso.categoria() != null) {
            this.categoria = dadosCurso.categoria();
        }
    }
}
