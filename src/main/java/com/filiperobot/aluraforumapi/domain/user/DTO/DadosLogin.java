package com.filiperobot.aluraforumapi.domain.user.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosLogin(@NotBlank @Email String email, @NotBlank String senha) {
}
