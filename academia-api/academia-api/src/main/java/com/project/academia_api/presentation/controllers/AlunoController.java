package com.project.academia_api.presentation.controllers;

import com.project.academia_api.aplication.ports.in.AlunoServicePort;
import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private static final Logger log = LoggerFactory.getLogger(AlunoController.class);

    @Autowired
    private AlunoServicePort alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> criarAluno(@Valid @RequestBody CadastrarAlunoRequest aluno) {
        AlunoResponse novoAluno = alunoService.cadastrarAluno(aluno);
        log.info("Aluno cadastrado com sucesso: {}", novoAluno);
        return ResponseEntity.ok(novoAluno);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AlunoResponse>> listarAlunos() {
        log.info("Recebendo requisicao para listar todos os alunos.");
        List<AlunoResponse> alunos = alunoService.listarAlunos();
        log.info("Lista de alunos retornada com sucesso. Total: {}", alunos.size());
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<AlunoResponse> buscarAlunoPorParametros(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nome
    ) {
        log.info("Recebendo requisição para buscar aluno por parametros");
        AlunoResponse aluno = alunoService.buscarAlunoPorParametros(id, cpf, nome);
        log.info("Aluno encontrado: {}", aluno);
        return ResponseEntity.ok(aluno);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizarAluno(@PathVariable Long id, @RequestBody AtualizarAlunoRequest aluno) {
        log.info("Recebendo requisição para atualizar aluno com ID: {}. Dados: {}", id, aluno);
        AlunoResponse alunoAtualizado = alunoService.atualizarAluno(id, aluno);
        log.info("Aluno atualizado com sucesso: {}", alunoAtualizado);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        log.info("Recebendo requisição para deletar aluno com ID: {}", id);
        alunoService.deletarAluno(id);
        log.info("Aluno com ID: {} deletado com sucesso.", id);
        return ResponseEntity.noContent().build();
    }
}
