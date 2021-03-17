package br.com.cep.controller;

import br.com.cep.CepApplication;
import br.com.cep.mass.EnderecoMass;
import br.com.cep.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = CepApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class EnderecoControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private EnderecoService enderecoService;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deve_validar_campos_para_cadastrar_novo_endereco() throws Exception {
        mockMvc.perform(post("/endereco")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Campos inválidos"))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(4)));


        mockMvc.perform(post("/endereco")
                .content(mapper.writeValueAsString(EnderecoMass.invalidNovoEnderecoDtoTestObject()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Campos inválidos"))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasItem("Atributo CEP deve ter 8 caracteres numéricos")))
                .andExpect(jsonPath("$.errors", hasSize(3)));

        verify(enderecoService, times(0)).save(any());
    }

    @Test
    public void deve_cadastrar_novo_endereco() throws Exception {
        mockMvc.perform(post("/endereco")
                .content(mapper.writeValueAsString(EnderecoMass.validNovoEnderecoDtoTestObject()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        verify(enderecoService, times(1)).save(any());
    }

    @Test
    public void deve_obter_endereco_por_cep() throws Exception {
        when(enderecoService.findByCep(any())).thenReturn(EnderecoMass.enderecoTestObject());

        mockMvc.perform(get("/endereco/00000000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cidade").value("Apucarana"))
                .andExpect(jsonPath("$.uf").value("PR"))
                .andExpect(jsonPath("$.bairro").value("Bairro Teste 1"))
                .andExpect(jsonPath("$.rua").value("Rua Teste 1"));

    }

    @Test
    public void deve_retornar_cep_invalido() throws Exception {
        when(enderecoService.findByCep(any())).thenReturn(EnderecoMass.enderecoTestObject());

        mockMvc.perform(get("/endereco/aaaaaaaa")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("findByCep.cep: Atributo CEP deve ter 8 caracteres numéricos"));

    }

}