package com.project.academia_api.presentation.dtos.request;

import lombok.Data;

@Data
public class AtualizarAlunoRequest {
    private String nome;
    private String telefone;
    private String plano;
    private String objetivo;
    private String status;
}
