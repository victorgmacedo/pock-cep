package br.com.cep.service;

import br.com.cep.exception.CidadeException;
import br.com.cep.model.Cidade;
import br.com.cep.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public Cidade save(Cidade cidade){
        if(cidadeRepository.hasIbge(cidade.getIbge())){
            throw new CidadeException(String.format("Cidade com ibge %s já cadastrada", cidade.getIbge()));
        }
        return cidadeRepository.save(cidade);
    }
    public boolean cityExists(Long id){
        return cidadeRepository.cityExists(id);
    }
    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }
}
