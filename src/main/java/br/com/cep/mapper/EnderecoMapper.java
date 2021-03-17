package br.com.cep.mapper;

import br.com.cep.dto.EnderecoDto;
import br.com.cep.dto.NovoEnderecoDto;
import br.com.cep.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface EnderecoMapper {

    @Mappings({
        @Mapping(target = "cidade", source = "cidade.nome"),
        @Mapping(target = "uf", source = "cidade.uf")
    })
    EnderecoDto toDto(Endereco endereco);
    @Mappings({
            @Mapping(target = "cidade.id", source = "idCidade")
    })
    Endereco toEndereco(NovoEnderecoDto enderecoDto);

}
