package com.filiperobot.aluraforumapi.domain.forum.topico;


import com.filiperobot.aluraforumapi.domain.course.Curso;
import com.filiperobot.aluraforumapi.domain.course.DTO.DadosCursoCompleto;
import com.filiperobot.aluraforumapi.domain.forum.resposta.Resposta;
import com.filiperobot.aluraforumapi.domain.forum.topico.DTO.DadosAtualizarTopico;
import com.filiperobot.aluraforumapi.domain.forum.topico.DTO.DadosCompletoCadastroTopico;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosUsuarioCompleto;
import com.filiperobot.aluraforumapi.domain.user.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Table(name = "topicos")
@Entity(name = "topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String mensagem;

    @Column(nullable = false)
    private final LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Resposta> respostas = new ArrayList<>();

    public Topico(DadosCompletoCadastroTopico dadosTopico) {
        this.titulo = dadosTopico.titulo();
        this.mensagem = dadosTopico.mensagem();
        this.autor = dadosTopico.autor();
        this.curso = dadosTopico.curso();
    }

    public DadosUsuarioCompleto getDadosAutor() {
        return new DadosUsuarioCompleto(this.autor);
    }

    public DadosCursoCompleto getDadosCurso() {
        return new DadosCursoCompleto(this.curso);
    }

    public void atualizar(DadosAtualizarTopico dadosTopico) {
        if (dadosTopico.titulo() != null) {
            this.titulo = dadosTopico.titulo();
        }

        if (dadosTopico.mensagem() != null) {
            this.mensagem = dadosTopico.mensagem();
        }

        if (dadosTopico.status() != null) {
            this.status = dadosTopico.status();
        }
    }
}
