package com.filiperobot.aluraforumapi.domain.forum.resposta;

import com.filiperobot.aluraforumapi.domain.forum.topico.Topico;
import com.filiperobot.aluraforumapi.domain.user.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "resposta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "topico")
    private Topico topico;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "autor")
    private Usuario autor;

    private Boolean solucao = false;
}
