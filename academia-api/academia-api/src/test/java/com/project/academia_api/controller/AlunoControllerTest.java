package com.project.academia_api.controller;

import com.project.academia_api.aplication.ports.in.AlunoServicePort;
import com.project.academia_api.presentation.controllers.AlunoController;
import com.project.academia_api.presentation.dtos.request.AtualizarAlunoRequest;
import com.project.academia_api.presentation.dtos.request.CadastrarAlunoRequest;
import com.project.academia_api.presentation.dtos.response.AlunoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoControllerTest {

    @Mock
    private AlunoServicePort alunoService;

    @InjectMocks
    private AlunoController alunoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new student successfully")
    void criarAlunoSuccessfully() {
        CadastrarAlunoRequest request = new CadastrarAlunoRequest();
        AlunoResponse response = new AlunoResponse();
        when(alunoService.cadastrarAluno(request)).thenReturn(response);

        ResponseEntity<AlunoResponse> result = alunoController.criarAluno(request);

        assertEquals(ResponseEntity.ok(response), result);
        verify(alunoService, times(1)).cadastrarAluno(request);
    }

    @Test
    @DisplayName("Should list all students successfully")
    void listarAlunosSuccessfully() {
        List<AlunoResponse> response = List.of(new AlunoResponse());
        when(alunoService.listarAlunos()).thenReturn(response);

        ResponseEntity<List<AlunoResponse>> result = alunoController.listarAlunos();

        assertEquals(ResponseEntity.ok(response), result);
        verify(alunoService, times(1)).listarAlunos();
    }

    @Test
    @DisplayName("Should return empty list when no students are found")
    void listarAlunosEmptyList() {
        when(alunoService.listarAlunos()).thenReturn(Collections.emptyList());

        ResponseEntity<List<AlunoResponse>> result = alunoController.listarAlunos();

        assertTrue(result.getBody().isEmpty());
        verify(alunoService, times(1)).listarAlunos();
    }

    @Test
    @DisplayName("Should find a student by parameters successfully")
    void buscarAlunoPorParametrosSuccessfully() {
        Long id = 1L;
        String cpf = "12345678900";
        String nome = "John Doe";
        AlunoResponse response = new AlunoResponse();
        when(alunoService.buscarAlunoPorParametros(id, cpf, nome)).thenReturn(response);

        ResponseEntity<AlunoResponse> result = alunoController.buscarAlunoPorParametros(id, cpf, nome);

        assertEquals(ResponseEntity.ok(response), result);
        verify(alunoService, times(1)).buscarAlunoPorParametros(id, cpf, nome);
    }

    @Test
    @DisplayName("Should update a student successfully")
    void atualizarAlunoSuccessfully() {
        Long id = 1L;
        AtualizarAlunoRequest request = new AtualizarAlunoRequest();
        AlunoResponse response = new AlunoResponse();
        when(alunoService.atualizarAluno(id, request)).thenReturn(response);

        ResponseEntity<AlunoResponse> result = alunoController.atualizarAluno(id, request);

        assertEquals(ResponseEntity.ok(response), result);
        verify(alunoService, times(1)).atualizarAluno(id, request);
    }

    @Test
    @DisplayName("Should delete a student successfully")
    void deletarAlunoSuccessfully() {
        Long id = 1L;

        ResponseEntity<Void> result = alunoController.deletarAluno(id);

        assertEquals(ResponseEntity.noContent().build(), result);
        verify(alunoService, times(1)).deletarAluno(id);
    }
}
