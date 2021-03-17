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
public class CidadeDto {

    private Long id;
    @NotEmpty(message = "Atributo nome é obrigatório")
    @Size(max = 60, min = 2, message = "Atributo nome deve ter entre 3 e 60 caracteres")
    private String nome;
    @NotEmpty(message = "Atributo uf é obrigatório")
    @Pattern(regexp = "[a-zA-Z]{2}", message = "Atributo UF deve ter 2 caracteres")
    private String uf;
    @NotNull(message = "Atributo ibge deve ser preenchido")
    private Long ibge;

}
