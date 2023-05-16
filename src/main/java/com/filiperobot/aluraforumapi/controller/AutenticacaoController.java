package com.filiperobot.aluraforumapi.controller;

import com.filiperobot.aluraforumapi.domain.user.DTO.DadosLogin;
import com.filiperobot.aluraforumapi.infra.security.DTO.DadosTokenJWT;
import com.filiperobot.aluraforumapi.domain.user.Usuario;
import com.filiperobot.aluraforumapi.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosLogin dadosLogin) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosLogin.email(), dadosLogin.senha());

        var authentication = authenticationManager.authenticate(authenticationToken);

        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT("Bearer", tokenJWT));
    }
}
