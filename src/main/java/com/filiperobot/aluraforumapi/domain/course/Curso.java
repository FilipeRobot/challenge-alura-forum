package com.filiperobot.aluraforumapi.domain.course;

import com.filiperobot.aluraforumapi.domain.course.DTO.DadosCurso;
import com.filiperobot.aluraforumapi.domain.course.DTO.DadosCursoAtualizar;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "curso")
@NoArgsConstructor
@AllArgsConstructor
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
