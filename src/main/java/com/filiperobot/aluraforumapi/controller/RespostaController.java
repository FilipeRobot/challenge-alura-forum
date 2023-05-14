package com.filiperobot.aluraforumapi.controller;

import com.filiperobot.aluraforumapi.domain.forum.resposta.DTO.*;
import com.filiperobot.aluraforumapi.domain.forum.resposta.Resposta;
import com.filiperobot.aluraforumapi.domain.forum.resposta.RespostaRepository;
import com.filiperobot.aluraforumapi.domain.forum.topico.TopicoRepository;
import com.filiperobot.aluraforumapi.domain.user.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/respostas")
@RequiredArgsConstructor
public class RespostaController {

    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosResposta> cadastrar(@RequestBody @Valid DadosCadastroResposta dadosResposta, UriComponentsBuilder uriBuilder) {
        var autor = usuarioRepository.findById(dadosResposta.autor()).orElseThrow(
                () -> new EntityNotFoundException("Autor não encontrado")
        );

        var topico = topicoRepository.findById(dadosResposta.topico()).orElseThrow(
                () -> new EntityNotFoundException("Topico não encontrado")
        );

        var dadosCompletoResposta = new DadosCompletoCadastroResposta(dadosResposta.mensagem(), autor, topico);

        Resposta resposta = respostaRepository.save(new Resposta(dadosCompletoResposta));

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosResposta(resposta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosResposta> buscar(@PathVariable Long id) {
        var resposta = respostaRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosResposta(resposta));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemResposta>> listarResposta(@PageableDefault Pageable pageable) {
        Page<DadosListagemResposta> listagemRespostas = respostaRepository.findAll(pageable).map(DadosListagemResposta::new);

        return ResponseEntity.ok(listagemRespostas);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemResposta> atualizar(@RequestBody DadosAtualizarResposta respostaAtualizada) {
        var resposta = respostaRepository.getReferenceById(respostaAtualizada.id());

        resposta.atualizar(respostaAtualizada);

        return ResponseEntity.ok(new DadosListagemResposta(resposta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        respostaRepository.findById(id).ifPresentOrElse(
                respostaRepository::delete,
                () -> {
                    throw new IllegalArgumentException("Não é possivel apagar essa resposta");
                }
        );

        return ResponseEntity.noContent().build();
    }
}

