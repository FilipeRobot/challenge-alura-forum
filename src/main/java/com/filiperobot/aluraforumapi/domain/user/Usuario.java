package com.filiperobot.aluraforumapi.domain.user;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "usuarios")
@Entity(name = "usuario")
@NoArgsConstructor
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

    public Usuario(DadosUsuario autor) {
    }
}
