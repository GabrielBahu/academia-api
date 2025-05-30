package com.project.academia_api.domain.exception;

public class AlunoNaoEncontradoException extends RuntimeException {
    public AlunoNaoEncontradoException(String detalhes) {
        super("Aluno não encontrado: " + detalhes);
    }
}