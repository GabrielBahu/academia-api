package com.project.academia_api.domain.exception;

public class CpfJaCadastradoException extends RuntimeException{
    public CpfJaCadastradoException(String cpf) {
        super("Já existe um aluno cadastrado com o CPF: " + cpf);
    }
}
