package com.filiperobot.aluraforumapi.domain.course;

import com.filiperobot.aluraforumapi.domain.course.DTO.DadosCadastroCurso;
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

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    public Curso(DadosCadastroCurso dadosCurso) {
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
