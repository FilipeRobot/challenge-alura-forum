package com.filiperobot.aluraforumapi.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosCadastroUsuario;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosUsuarioAtualizar;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuarios")
@Entity(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    public Usuario(DadosCadastroUsuario dadosUsuario) {
        this.nome = dadosUsuario.nome();
        this.email = dadosUsuario.email();
        this.senha = dadosUsuario.senha();
    }

    public void atualizar(DadosUsuarioAtualizar dadosUsuario) {
        if (dadosUsuario.nome() != null) {
            this.nome = dadosUsuario.nome();
        }
        if (dadosUsuario.senha() != null) {
            this.senha = dadosUsuario.senha();
        }
    }
}
