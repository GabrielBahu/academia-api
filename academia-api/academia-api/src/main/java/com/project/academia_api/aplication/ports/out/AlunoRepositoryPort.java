package com.project.academia_api.aplication.ports.out;

import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;

import java.util.List;

public interface AlunoRepositoryPort {

    AlunoResponse cadastrarAluno(CadastrarAlunoRequest aluno);

    List<AlunoResponse> listarAlunos();

    AlunoResponse buscarAlunoPorParametros(Long id, String cpf, String nome);

    AlunoResponse atualizarAluno(Long id, AtualizarAlunoRequest aluno);

    void deletarAluno(Long id);
}
