package com.filiperobot.aluraforumapi.domain.forum.resposta;

import com.filiperobot.aluraforumapi.domain.forum.topico.Topico;
import com.filiperobot.aluraforumapi.domain.user.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "resposta")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico")
    private Topico topico;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    private Boolean solucao = false;
}
