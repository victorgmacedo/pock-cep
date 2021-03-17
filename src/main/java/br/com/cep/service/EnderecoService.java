package br.com.cep.service;

import br.com.cep.exception.CepException;
import br.com.cep.exception.CidadeException;
import br.com.cep.model.Cidade;
import br.com.cep.model.Endereco;
import br.com.cep.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.*;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final CidadeService cidadeService;

    public void save(Endereco endereco){
        if(enderecoRepository.hasCep(endereco.getCep())){
            throw new CepException(String.format("Cep %s já cadastrado", endereco.getCep()));
        }
        if(!cidadeService.cityExists(endereco.getCidade().getId()))
            throw new CidadeException("Cidade não encotrada");
        enderecoRepository.save(endereco);
    }

    public Endereco findByCep(String cep){
        Endereco endereco = enderecoRepository.findByCep(cep);
        int count = 0;
        while(isNull(endereco) && count < 8){
            cep = replaceLastDigit(cep, count);
            endereco = enderecoRepository.findByCep(cep);
            count++;
        }
        return Optional.ofNullable(endereco).orElseThrow(()-> new CepException("CEP inválido"));
    }

    private String replaceLastDigit(String cep, int totalReplacement){
        StringBuilder builder = new StringBuilder(cep);
        builder.setCharAt(cep.length() - ++totalReplacement, '0');
        return builder.toString();
    }

}
