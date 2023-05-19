package com.filiperobot.aluraforumapi.domain.user.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosUsuarioAtualizar(
        @NotNull(message = "{id.obrigatorio}")
        Long id,
        String nome,
        @Size(min = 4, message = "{senha.invalida}")
        String senha) {

        public DadosUsuarioAtualizar(DadosUsuarioAtualizar usuario, String senhaCriptografada) {
                this(
                        usuario.id(),
                        usuario.nome,
                        senhaCriptografada
                );
        }
}
