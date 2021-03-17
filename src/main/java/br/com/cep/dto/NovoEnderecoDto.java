package br.com.cep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovoEnderecoDto {

    @NotEmpty(message = "Atributo CEP deve ser informado")
    @Pattern(regexp = "[0-9]{8}", message = "Atributo CEP deve ter 8 caracteres numéricos")
    private String cep;
    @NotEmpty(message = "Atributo Rua dever ser informada")
    @Size(max = 120, message = "Atributo Rua deve ter no maximo 120 caracteres")
    private String rua;
    @NotEmpty(message = "Atributo Bairro dever ser informado")
    @Size(max = 60, message = "Atributo Bairro deve ter no maximo 60 caracteres")
    private String bairro;
    @NotNull(message = "Id da cidade deve ser informado. Consultar cidades disponiveis em /cidade")
    private Long idCidade;

}
