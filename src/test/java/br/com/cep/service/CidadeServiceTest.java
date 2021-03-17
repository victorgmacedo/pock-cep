package br.com.cep.service;

import br.com.cep.exception.CidadeException;
import br.com.cep.mass.CidadeMass;
import br.com.cep.model.Cidade;
import br.com.cep.repository.CidadeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class CidadeServiceTest {

    @Mock
    CidadeRepository cidadeRepository;

    @InjectMocks
    CidadeService cidadeService;

    @Test
    public void deve_salvar_cidade(){
        Cidade cidade = CidadeMass.cidadeTestObject();
        cidadeService.save(cidade);
        verify(cidadeRepository).save(cidade);
    }

    @Test(expected = CidadeException.class)
    public void deve_validar_ibge_ja_cadastrado(){
        when(cidadeRepository.hasIbge(any())).thenReturn(true);
        cidadeService.save(CidadeMass.cidadeTestObject());
    }

    @Test
    public void deve_trazer_registros(){
        when(cidadeRepository.findAll()).thenReturn(List.of(CidadeMass.cidadeTestObject()));
        List<Cidade> cidades = cidadeService.findAll();
        assertThat(cidades.size(), equalTo(1));
    }

}