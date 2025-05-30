package com.project.academia_api.service;

import com.project.academia_api.aplication.ports.out.AlunoRepositoryPort;
import com.project.academia_api.aplication.usecases.AlunoServiceImpl;
import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceImplTest {

    @Mock
    private AlunoRepositoryPort alunoRepository;

    @InjectMocks
    private AlunoServiceImpl alunoService;

    @Test
    void cadastrarAlunoShouldReturnAlunoResponseWhenValidRequest() {
        CadastrarAlunoRequest request = new CadastrarAlunoRequest();
        AlunoResponse expectedResponse = new AlunoResponse();

        when(alunoRepository.cadastrarAluno(request)).thenReturn(expectedResponse);

        AlunoResponse response = alunoService.cadastrarAluno(request);

        assertEquals(expectedResponse, response);
        verify(alunoRepository).cadastrarAluno(request);
    }

    @Test
    void listarAlunosShouldReturnEmptyListWhenNoAlunosExist() {
        when(alunoRepository.listarAlunos()).thenReturn(Collections.emptyList());

        List<AlunoResponse> response = alunoService.listarAlunos();

        assertTrue(response.isEmpty());
        verify(alunoRepository).listarAlunos();
    }

    @Test
    void buscarAlunoPorParametrosShouldReturnAlunoResponseWhenValidParameters() {
        Long id = 1L;
        String cpf = "12345678900";
        String nome = "João";
        AlunoResponse expectedResponse = new AlunoResponse();

        when(alunoRepository.buscarAlunoPorParametros(id, cpf, nome)).thenReturn(expectedResponse);

        AlunoResponse response = alunoService.buscarAlunoPorParametros(id, cpf, nome);

        assertEquals(expectedResponse, response);
        verify(alunoRepository).buscarAlunoPorParametros(id, cpf, nome);
    }

    @Test
    void atualizarAlunoShouldThrowExceptionWhenAlunoNotFound() {
        Long id = 1L;
        AtualizarAlunoRequest request = new AtualizarAlunoRequest();

        when(alunoRepository.atualizarAluno(id, request)).thenThrow(new RuntimeException("Aluno não encontrado"));

        assertThrows(RuntimeException.class, () -> alunoService.atualizarAluno(id, request));
        verify(alunoRepository).atualizarAluno(id, request);
    }

    @Test
    void deletarAlunoShouldNotThrowExceptionWhenAlunoExists() {
        Long id = 1L;

        doNothing().when(alunoRepository).deletarAluno(id);

        assertDoesNotThrow(() -> alunoService.deletarAluno(id));
        verify(alunoRepository).deletarAluno(id);
    }
}