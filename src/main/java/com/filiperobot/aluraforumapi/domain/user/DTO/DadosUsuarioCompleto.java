package com.filiperobot.aluraforumapi.domain.user.DTO;

import com.filiperobot.aluraforumapi.domain.user.Usuario;

public record DadosUsuarioCompleto(Long id, String nome, String email, String senha) {
    public DadosUsuarioCompleto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }
}
