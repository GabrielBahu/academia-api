package com.project.academia_api.presentation.dtos.request;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class CadastrarAlunoRequest {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos.")
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter 10 ou 11 dígitos numéricos.")
    private String telefone;

    @NotBlank(message = "O plano é obrigatório.")
    private String plano;

    @NotBlank(message = "A data de início é obrigatória.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data de início deve estar no formato YYYY-MM-DD.")
    private String dataInicio;

    private String objetivo;
}
