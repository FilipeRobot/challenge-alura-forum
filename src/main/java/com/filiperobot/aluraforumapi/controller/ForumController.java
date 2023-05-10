package com.filiperobot.aluraforumapi.controller;

import com.filiperobot.aluraforumapi.domain.course.CursoRepository;
import com.filiperobot.aluraforumapi.domain.forum.topico.*;
import com.filiperobot.aluraforumapi.domain.forum.topico.DTO.*;
import com.filiperobot.aluraforumapi.domain.user.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
public class ForumController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/topicos")
    @Transactional
    public ResponseEntity<DadosTopicoCompleto> criarTopico(
            @RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder) {
        var usuario = usuarioRepository.getReferenceById(dados.autor());

        var curso = cursoRepository.getReferenceById(dados.curso());

        var dadosCadastroTopico = new DadosCompletoCadastroTopico(
                dados.titulo(),
                dados.mensagem(),
                usuario, curso
        );

        var topico = topicoRepository.save(new Topico(dadosCadastroTopico));

        var uri = uriBuilder.path("/forum/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosTopicoCompleto(topico));
    }

    @GetMapping("/topicos/{id}")
    public ResponseEntity<DadosTopicoCompleto> mostrarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosTopicoCompleto(topico));
    }

    @GetMapping("/topicos")
    public ResponseEntity<Page<DadosListagemTopico>> mostrarTodosOsTopicos(@PageableDefault Pageable pageable) {

        Page<DadosListagemTopico> listagemTopicos = topicoRepository.findAll(pageable).map(DadosListagemTopico::new);

        return ResponseEntity.ok(listagemTopicos);
    }

    @PutMapping("/topicos")
    @Transactional
    public ResponseEntity<DadosListagemTopico> atualizar(@RequestBody @Valid DadosAtualizarTopico dadosTopicoAtualizacao) {
        var topico = topicoRepository.getReferenceById(dadosTopicoAtualizacao.id());

        topico.atualizar(dadosTopicoAtualizacao);

        return ResponseEntity.ok(new DadosListagemTopico(topico));
    }

    @DeleteMapping("/topicos/{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        topicoRepository.findById(id).ifPresentOrElse(
                topicoRepository::delete,
                ()  -> {
                    throw new IllegalArgumentException("Tópico não existe, não é possível deleta-lo");
                }
        );

        return ResponseEntity.noContent().build();
    }
}
