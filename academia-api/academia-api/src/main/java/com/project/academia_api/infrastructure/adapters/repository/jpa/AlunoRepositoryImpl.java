package com.project.academia_api.infrastructure.adapters.repository.jpa;

import com.project.academia_api.aplication.ports.out.AlunoRepositoryPort;
import com.project.academia_api.domain.exception.AlunoNaoEncontradoException;
import com.project.academia_api.domain.exception.CpfJaCadastradoException;
import com.project.academia_api.domain.model.AlunoEntity;
import com.project.academia_api.infrastructure.mappers.AlunoEntityMapper;
import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlunoRepositoryImpl implements AlunoRepositoryPort {

    @Autowired
    private AlunoSpringRepository repository;

    @Autowired
    private AlunoEntityMapper mapper;

    public AlunoResponse cadastrarAluno(CadastrarAlunoRequest aluno) {

        if (repository.existsByCpf(aluno.getCpf())) {
            throw new CpfJaCadastradoException(aluno.getCpf());
        }

        AlunoEntity entity = mapper.cadastraRequestToEntity(aluno);
        entity.setStatus("Ativo");

        entity = repository.save(entity);

        return mapper.entityToResponse(entity);
    }

    @Override
    public List<AlunoResponse> listarAlunos() {

        List<AlunoEntity> alunos = repository.findAll();

        if (alunos.isEmpty()) {
            return List.of();
        }

        return alunos.stream().map(mapper::entityToResponse).toList();
    }

    @Override
    public AlunoResponse buscarAlunoPorParametros(Long id, String cpf, String nome) {
        AlunoEntity aluno = repository.findByParams(id, cpf, nome);

        if (aluno == null) {
            throw new AlunoNaoEncontradoException("id=" + id + ", cpf=" + cpf + ", nome=" + nome);
        }

        return mapper.entityToResponse(aluno);
    }

    @Override
    public AlunoResponse atualizarAluno(Long id, AtualizarAlunoRequest aluno) {
        AlunoEntity entity = repository.findById(id).orElseThrow(() ->
                new AlunoNaoEncontradoException("Aluno com ID " + id + " não encontrado.")
        );

        AlunoEntity alunoEntity = mapper.atualizaRequestToEntity(aluno, entity);

        repository.save(alunoEntity);

        return mapper.entityToResponse(alunoEntity);
    }


    @Override
    public void deletarAluno(Long id) {
        AlunoEntity entity = repository.findById(id).orElseThrow(() ->
                new AlunoNaoEncontradoException("Aluno com ID " + id + " não encontrado.")
        );

        repository.delete(entity);
    }

}
