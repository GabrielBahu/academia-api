package com.project.academia_api.infrastructure.mappers;

import com.project.academia_api.domain.model.AlunoEntity;
import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-29T13:36:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AlunoEntityMapperImpl implements AlunoEntityMapper {

    @Override
    public AlunoEntity cadastraRequestToEntity(CadastrarAlunoRequest request) {
        if ( request == null ) {
            return null;
        }

        AlunoEntity alunoEntity = new AlunoEntity();

        alunoEntity.setNome( request.getNome() );
        alunoEntity.setCpf( request.getCpf() );
        alunoEntity.setTelefone( request.getTelefone() );
        alunoEntity.setPlano( request.getPlano() );
        alunoEntity.setDataInicio( request.getDataInicio() );
        alunoEntity.setObjetivo( request.getObjetivo() );

        return alunoEntity;
    }

    @Override
    public AlunoResponse entityToResponse(AlunoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        AlunoResponse alunoResponse = new AlunoResponse();

        alunoResponse.setId( entity.getId() );
        alunoResponse.setNome( entity.getNome() );
        alunoResponse.setCpf( entity.getCpf() );
        alunoResponse.setTelefone( entity.getTelefone() );
        alunoResponse.setPlano( entity.getPlano() );
        alunoResponse.setDataInicio( entity.getDataInicio() );
        alunoResponse.setStatus( entity.getStatus() );
        alunoResponse.setObjetivo( entity.getObjetivo() );

        return alunoResponse;
    }

    @Override
    public AlunoEntity atualizaRequestToEntity(AtualizarAlunoRequest aluno, AlunoEntity entity) {
        if ( aluno == null && entity == null ) {
            return null;
        }

        AlunoEntity alunoEntity = new AlunoEntity();

        if ( entity != null ) {
            alunoEntity.setCpf( entity.getCpf() );
            alunoEntity.setDataInicio( entity.getDataInicio() );
            alunoEntity.setId( entity.getId() );
        }
        alunoEntity.setNome( aluno.getNome() != null ? aluno.getNome() : entity.getNome() );
        alunoEntity.setTelefone( aluno.getTelefone() != null ? aluno.getTelefone() : entity.getTelefone() );
        alunoEntity.setPlano( aluno.getPlano() != null ? aluno.getPlano() : entity.getPlano() );
        alunoEntity.setObjetivo( aluno.getObjetivo() != null ? aluno.getObjetivo() : entity.getObjetivo() );
        alunoEntity.setStatus( aluno.getStatus() != null ? aluno.getStatus() : entity.getStatus() );

        return alunoEntity;
    }
}
