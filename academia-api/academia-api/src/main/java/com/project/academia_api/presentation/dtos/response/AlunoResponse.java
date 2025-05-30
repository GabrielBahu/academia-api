package com.project.academia_api.presentation.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponse {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String plano;
    private String dataInicio;
    private String status;
    private String objetivo;
}
