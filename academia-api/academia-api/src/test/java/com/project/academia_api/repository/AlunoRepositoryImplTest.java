package com.project.academia_api.repository;

import com.project.academia_api.domain.exception.AlunoNaoEncontradoException;
import com.project.academia_api.domain.exception.CpfJaCadastradoException;
import com.project.academia_api.domain.model.AlunoEntity;
import com.project.academia_api.infrastructure.adapters.repository.jpa.AlunoRepositoryImpl;
import com.project.academia_api.infrastructure.adapters.repository.jpa.AlunoSpringRepository;
import com.project.academia_api.infrastructure.mappers.AlunoEntityMapper;
import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoRepositoryImplTest {

    @Mock
    private AlunoSpringRepository repository;

    @Mock
    private AlunoEntityMapper mapper;

    @InjectMocks
    private AlunoRepositoryImpl alunoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Cadastrar aluno com sucesso")
    @Test
    void cadastrarAlunoComSucesso() {
        CadastrarAlunoRequest request = new CadastrarAlunoRequest();
        AlunoEntity entity = new AlunoEntity();
        AlunoResponse response = new AlunoResponse();

        when(repository.existsByCpf(request.getCpf())).thenReturn(false);
        when(mapper.cadastraRequestToEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.entityToResponse(entity)).thenReturn(response);

        AlunoResponse result = alunoRepository.cadastrarAluno(request);

        Assertions.assertEquals(response, result);
        verify(repository).existsByCpf(request.getCpf());
        verify(repository).save(entity);
    }

    @DisplayName("Cadastrar aluno com CPF já cadastrado")
    @Test
    void cadastrarAlunoComCpfJaCadastrado() {
        CadastrarAlunoRequest request = new CadastrarAlunoRequest();

        when(repository.existsByCpf(request.getCpf())).thenReturn(true);

        Assertions.assertThrows(CpfJaCadastradoException.class, () -> alunoRepository.cadastrarAluno(request));
        verify(repository).existsByCpf(request.getCpf());
        verifyNoInteractions(mapper);
    }

    @DisplayName("Buscar aluno por parâmetros inexistentes")
    @Test
    void buscarAlunoPorParametrosInexistentes() {
        Long id = 1L;
        String cpf = "12345678900";
        String nome = "João Silva";

        when(repository.findByParams(id, cpf, nome)).thenReturn(null);

        Assertions.assertThrows(AlunoNaoEncontradoException.class, () -> alunoRepository.buscarAlunoPorParametros(id, cpf, nome));
        verify(repository).findByParams(id, cpf, nome);
    }

    @DisplayName("Atualizar aluno com ID inexistente")
    @Test
    void atualizarAlunoComIdInexistente() {
        Long id = 1L;
        AtualizarAlunoRequest request = new AtualizarAlunoRequest();

        when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(AlunoNaoEncontradoException.class, () -> alunoRepository.atualizarAluno(id, request));
        verify(repository).findById(id);
        verifyNoInteractions(mapper);
    }

    @DisplayName("Deletar aluno com ID inexistente")
    @Test
    void deletarAlunoComIdInexistente() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(AlunoNaoEncontradoException.class, () -> alunoRepository.deletarAluno(id));
        verify(repository).findById(id);
    }
}
