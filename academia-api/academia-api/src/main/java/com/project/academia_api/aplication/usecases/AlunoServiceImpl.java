package com.project.academia_api.aplication.usecases;

import com.project.academia_api.aplication.ports.in.AlunoServicePort;
import com.project.academia_api.aplication.ports.out.AlunoRepositoryPort;
import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoServicePort {

    @Autowired
    private AlunoRepositoryPort alunoRepository;

    @Override
    public AlunoResponse cadastrarAluno(CadastrarAlunoRequest aluno) {
        return alunoRepository.cadastrarAluno(aluno);
    }

    @Override
    public List<AlunoResponse> listarAlunos() {
        return alunoRepository.listarAlunos();
    }

    @Override
    public AlunoResponse buscarAlunoPorParametros(Long id, String cpf, String nome) {
        return alunoRepository.buscarAlunoPorParametros(id, cpf, nome);
    }

    @Override
    public AlunoResponse atualizarAluno(Long id, AtualizarAlunoRequest aluno) {
        return alunoRepository.atualizarAluno(id, aluno);
    }

    @Override
    public void deletarAluno(Long id) {
        alunoRepository.deletarAluno(id);
    }
}
