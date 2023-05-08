package com.filiperobot.aluraforumapi.controller;

import com.filiperobot.aluraforumapi.domain.course.CursoRepository;
import com.filiperobot.aluraforumapi.domain.forum.topico.*;
import com.filiperobot.aluraforumapi.domain.user.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/forum")
public class ForumController {

    /* TODO
            [*] - Criar um novo topico
            [*] - mostrar todos os topicos criados (Usar paginação)
            [*] - mostrar um topico especifico
            [] - atualizar topico
            [] - eliminar topico
     */
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public ForumController (TopicoRepository topicoRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/topicos")
    @Transactional
    public ResponseEntity<DadosTopicoCompleto> criarTopico(
            @RequestBody DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder) {

        var usuario = usuarioRepository.getReferenceById(dados.autor());

        var curso = cursoRepository.getReferenceById(dados.curso());

        var dadosCadastroTopico = new DadosCompletoCadastroTopico(
                dados.titulo(),
                dados.mensagem(),
                usuario, curso
        );

        Topico topico = topicoRepository.save(new Topico(dadosCadastroTopico));

        var uri = uriBuilder.path("/forum/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosTopicoCompleto(topico));
    }

    @GetMapping("/topicos/{id}")
    public ResponseEntity<DadosTopicoCompleto> mostrarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosTopicoCompleto(topico));
    }

    @GetMapping("/topicos")
    public ResponseEntity<Page<DadosListagemTopico>> mostrarTodosOsTopicos(@PageableDefault(size = 10)Pageable pageable) {
        Page<DadosListagemTopico> listagemTopicos = topicoRepository.findAll(pageable).map(DadosListagemTopico::new);

        return ResponseEntity.ok(listagemTopicos);
    }
}
