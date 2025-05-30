package com.project.academia_api.infrastructure.mappers;

import com.project.academia_api.domain.model.AlunoEntity;
import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoEntityMapper {

    AlunoEntity cadastraRequestToEntity(CadastrarAlunoRequest request);

    AlunoResponse entityToResponse(AlunoEntity entity);

    @Mapping(target = "nome", expression = "java(aluno.getNome() != null ? aluno.getNome() : entity.getNome())")
    @Mapping(target = "cpf", source = "entity.cpf")
    @Mapping(target = "telefone", expression = "java(aluno.getTelefone() != null ? aluno.getTelefone() : entity.getTelefone())")
    @Mapping(target = "plano", expression = "java(aluno.getPlano() != null ? aluno.getPlano() : entity.getPlano())")
    @Mapping(target = "objetivo", expression = "java(aluno.getObjetivo() != null ? aluno.getObjetivo() : entity.getObjetivo())")
    @Mapping(target = "status", expression = "java(aluno.getStatus() != null ? aluno.getStatus() : entity.getStatus())")
    @Mapping(target = "dataInicio", source = "entity.dataInicio")
    AlunoEntity atualizaRequestToEntity(AtualizarAlunoRequest aluno, AlunoEntity entity);
}
