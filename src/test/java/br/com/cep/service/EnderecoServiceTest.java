package br.com.cep.service;

import br.com.cep.exception.CepException;
import br.com.cep.exception.CidadeException;
import br.com.cep.mass.EnderecoMass;
import br.com.cep.model.Endereco;
import br.com.cep.repository.EnderecoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class EnderecoServiceTest {

    @Mock
    EnderecoRepository enderecoRepository;

    @Mock
    CidadeService cidadeService;

    @InjectMocks
    EnderecoService enderecoService;

    @Test
    public void deve_alterar_todas_posicoes_para_zero_ate_encontrar_cep(){
        when(enderecoRepository.findByCep("00000000")).thenReturn(EnderecoMass.enderecoTestObject());
        enderecoService.findByCep("99999999");
        verify(enderecoRepository, times(1)).findByCep("99999999");
        verify(enderecoRepository, times(1)).findByCep("99999990");
        verify(enderecoRepository, times(1)).findByCep("99999900");
        verify(enderecoRepository, times(1)).findByCep("99999000");
        verify(enderecoRepository, times(1)).findByCep("99990000");
        verify(enderecoRepository, times(1)).findByCep("99000000");
        verify(enderecoRepository, times(1)).findByCep("90000000");
        verify(enderecoRepository, times(1)).findByCep("00000000");
    }

    @Test(expected = CepException.class)
    public void deve_alterar_todas_posicoes_para_zero_e_retornar_erro(){
        enderecoService.findByCep("99999999");
    }

    @Test(expected = CepException.class)
    public void deve_validar_cep_ja_cadastrado(){
        when(enderecoRepository.hasCep(any())).thenReturn(true);
        enderecoService.save(EnderecoMass.enderecoTestObject());
    }

    @Test(expected = CidadeException.class)
    public void deve_validar_cidade_inexistente(){
        when(enderecoRepository.hasCep(any())).thenReturn(false);
        when(cidadeService.cityExists(any())).thenReturn(false);
        enderecoService.save(EnderecoMass.enderecoTestObject());
    }

    @Test
    public void deve_salvar_endereco(){
        when(enderecoRepository.hasCep(any())).thenReturn(false);
        when(cidadeService.cityExists(any())).thenReturn(true);
        Endereco endereco = EnderecoMass.enderecoTestObject();
        enderecoService.save(endereco);
        verify(enderecoRepository).save(endereco);
    }

}