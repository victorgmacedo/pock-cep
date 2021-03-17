package br.com.cep.dto;

import lombok.Data;

@Data
public class EnderecoDto {

    private String rua;
    private String bairro;
    private String cidade;
    private String uf;

}
