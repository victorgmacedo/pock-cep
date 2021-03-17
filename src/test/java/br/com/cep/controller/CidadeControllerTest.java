package br.com.cep.controller;

import br.com.cep.CepApplication;
import br.com.cep.mass.CidadeMass;
import br.com.cep.mass.EnderecoMass;
import br.com.cep.service.CidadeService;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = CepApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CidadeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CidadeService cidadeService;

    private ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deve_validar_campos_para_cadastrar_nova_cidade() throws Exception {
        mockMvc.perform(post("/cidade")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Campos inválidos"))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(3)));


        mockMvc.perform(post("/cidade")
                .content(mapper.writeValueAsString(CidadeMass.invalidCidadeDtoTestObject()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Campos inválidos"))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasItem("Atributo UF deve ter 2 caracteres")))
                .andExpect(jsonPath("$.errors", hasSize(2)));

        verify(cidadeService, times(0)).save(any());
    }

    @Test
    public void deve_cadastrar_nova_cidade() throws Exception {
        mockMvc.perform(post("/cidade")
                .content(mapper.writeValueAsString(CidadeMass.validCidadeDtoTestObject()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(cidadeService, times(1)).save(any());
    }

    @Test
    public void deve_retornar_cidades_cadastradas() throws Exception {
        when(cidadeService.findAll()).thenReturn(List.of(CidadeMass.cidadeTestObject()));
        mockMvc.perform(get("/cidade")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$",hasSize(1)));
        verify(cidadeService, times(1)).findAll();
    }
}