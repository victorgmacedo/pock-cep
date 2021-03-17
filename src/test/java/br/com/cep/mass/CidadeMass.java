package br.com.cep.mass;

import br.com.cep.dto.CidadeDto;
import br.com.cep.model.Cidade;
import br.com.cep.util.TestUtils;

public class CidadeMass {

    public static Cidade cidadeTestObject(){
        return new Cidade(1L,"Apucarana","PR",4101408L);
    }


    public static CidadeDto invalidCidadeDtoTestObject() {
        return new CidadeDto(null, TestUtils.genarerateString(61),"12",1234567890L);
    }
    public static CidadeDto validCidadeDtoTestObject() {
        return new CidadeDto(null,"Apucarana","PR",4101408L);
    }
}
