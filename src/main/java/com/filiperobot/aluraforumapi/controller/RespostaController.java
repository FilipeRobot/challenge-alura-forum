package com.filiperobot.aluraforumapi.controller;

import com.filiperobot.aluraforumapi.domain.forum.resposta.DTO.DadosCadastroResposta;
import com.filiperobot.aluraforumapi.domain.forum.resposta.DTO.DadosResposta;
import com.filiperobot.aluraforumapi.domain.forum.resposta.Resposta;
import com.filiperobot.aluraforumapi.domain.forum.resposta.RespostaRepository;
import com.filiperobot.aluraforumapi.domain.forum.topico.TopicoRepository;
import com.filiperobot.aluraforumapi.domain.user.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respostas")
@RequiredArgsConstructor
public class RespostaController {

    private final RespostaRepository respostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroResposta dadosResposta) {
        var topico = topicoRepository.getReferenceById(1L);

        Resposta resposta = new Resposta(dadosResposta, topico);

        System.out.println(resposta);

        Resposta save = respostaRepository.save(resposta);

        System.out.println(save);
    }

    @GetMapping("/{id}")
    public DadosResposta buscar(@PathVariable Long id) {
        var resposta = respostaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Resposta não encontrada")
        );


        DadosResposta dadosResposta = new DadosResposta(resposta);
        System.out.println(dadosResposta);
        return dadosResposta;
    }
}

