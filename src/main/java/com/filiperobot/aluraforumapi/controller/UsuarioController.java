package com.filiperobot.aluraforumapi.controller;

import com.filiperobot.aluraforumapi.domain.user.*;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosCadastroUsuario;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosUsuarioAtualizar;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosUsuarioCompleto;
import com.filiperobot.aluraforumapi.domain.user.DTO.DadosListagemUsuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosUsuarioCompleto> cadastrar(
            @RequestBody @Valid DadosCadastroUsuario dadosUsuario, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioRepository.save(new Usuario(dadosUsuario));

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosUsuarioCompleto(usuario));
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosUsuarioCompleto> usuario(@PathVariable Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosUsuarioCompleto(usuario));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> listarUsuarios() {
        var usuarios = usuarioRepository
                .findAll()
                .stream()
                .map(DadosListagemUsuario::new)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosUsuarioCompleto> atualizar(@RequestBody @Valid DadosUsuarioAtualizar dadosUsuarioAtualizacao) {
        var usuario = usuarioRepository.getReferenceById(dadosUsuarioAtualizacao.id());
        usuario.atualizar(dadosUsuarioAtualizacao);

        return ResponseEntity.ok(new DadosUsuarioCompleto(usuario));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> removerUsuario(@PathVariable Long id) {
        usuarioRepository.findById(id).ifPresentOrElse(
                usuarioRepository::delete,
                () -> {
                    throw new IllegalArgumentException("Usuário não existe, não é possível deleta-lo");
                }
        );

        return ResponseEntity.noContent().build();
    }
}
