package com.filiperobot.aluraforumapi.domain.user;

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
