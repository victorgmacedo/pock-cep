package br.com.cep.mass;

import br.com.cep.dto.NovoEnderecoDto;
import br.com.cep.model.Endereco;
import br.com.cep.util.TestUtils;

public class EnderecoMass {

    public static Endereco enderecoTestObject(){
        return new Endereco(null,"00000000","Rua Teste 1","Bairro Teste 1",CidadeMass.cidadeTestObject());
    }

    public static NovoEnderecoDto invalidNovoEnderecoDtoTestObject(){
        return new NovoEnderecoDto("0", TestUtils.genarerateString(121),TestUtils.genarerateString(61),1L);
    }

    public static NovoEnderecoDto validNovoEnderecoDtoTestObject(){
        return new NovoEnderecoDto("12345678", TestUtils.genarerateString(120),TestUtils.genarerateString(60),1L);
    }


}
