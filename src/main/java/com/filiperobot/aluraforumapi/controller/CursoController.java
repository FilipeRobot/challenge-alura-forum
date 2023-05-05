package com.filiperobot.aluraforumapi.controller;

import com.filiperobot.aluraforumapi.domain.course.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCursoCompleto> cadastrar(
            @RequestBody @Valid DadosCurso dadosCurso, UriComponentsBuilder uriBuilder) {
        var curso = cursoRepository.save(new Curso(dadosCurso));

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosCursoCompleto(curso));
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosCursoCompleto> curso(@PathVariable Long id) {
        var curso = cursoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosCursoCompleto(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DadosCursoCompleto>> listaCursos(@PageableDefault(size = 10)Pageable pageable) {
        Page<DadosCursoCompleto> cursos = cursoRepository.findAll(pageable).map(DadosCursoCompleto::new);

        return ResponseEntity.ok(cursos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosCursoCompleto> atualizar(@RequestBody @Valid DadosCursoAtualizar dadosCursoAtualizacao) {
        var curso = cursoRepository.getReferenceById(dadosCursoAtualizacao.id());

        curso.atualizar(dadosCursoAtualizacao);

        return ResponseEntity.ok(new DadosCursoCompleto(curso));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long id){
//        cursoRepository.deleteById(id);
        cursoRepository.findById(id).ifPresentOrElse(
                cursoRepository::delete,
                ()  -> {System.out.println("Não é possível excluir curso");});

        return ResponseEntity.noContent().build();
    }
}
