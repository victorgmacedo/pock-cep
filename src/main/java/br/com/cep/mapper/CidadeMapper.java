package br.com.cep.mapper;

import br.com.cep.dto.CidadeDto;
import br.com.cep.model.Cidade;
import org.mapstruct.Mapper;

@Mapper
public interface CidadeMapper {

    CidadeDto toDto(Cidade cidade);
    Cidade toCidade(CidadeDto cidadeDto);

}
