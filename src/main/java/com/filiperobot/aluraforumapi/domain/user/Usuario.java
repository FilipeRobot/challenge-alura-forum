package com.filiperobot.aluraforumapi.domain.user;

import com.filiperobot.aluraforumapi.domain.user.DTO.DadosUsuario;
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
    private String nome;
    private String email;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    public Usuario(DadosUsuario dadosUsuario) {
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
