package com.filiperobot.aluraforumapi.controller;

import com.filiperobot.aluraforumapi.domain.user.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosUsuarioCompleto> cadastrar(
            @RequestBody @Valid DadosUsuario dadosUsuario, UriComponentsBuilder uriBuilder) {
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
    public ResponseEntity<List<DadosUsuarioCompleto>> listarUsuarios() {
        var usuarios = usuarioRepository
                .findAll()
                .stream()
                .map(DadosUsuarioCompleto::new)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosUsuarioCompleto> atualizar(@RequestBody DadosUsuarioAtualizar dadosUsuarioAtualizacao) {
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
                    System.out.println("Não é possível excluir usuário");
                }
        );

        return ResponseEntity.noContent().build();
    }
}
