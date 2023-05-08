package com.filiperobot.aluraforumapi.domain.forum.topico;


import com.filiperobot.aluraforumapi.domain.course.Curso;
import com.filiperobot.aluraforumapi.domain.course.DadosCursoCompleto;
import com.filiperobot.aluraforumapi.domain.forum.resposta.Resposta;
import com.filiperobot.aluraforumapi.domain.user.DadosUsuario;
import com.filiperobot.aluraforumapi.domain.user.DadosUsuarioCompleto;
import com.filiperobot.aluraforumapi.domain.user.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Table(name = "topicos")
@Entity(name = "topico")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "curso")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas = new ArrayList<>();

    public Topico(int qtd) {
        // Criar uma "qtd"(quantidade) de respostas vazias em um topico também vazio
        for (int i = 0; i < qtd; i++){
            respostas.add(new Resposta());
        }
    }

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
}
