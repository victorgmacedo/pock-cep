package br.com.cep.controller;

import br.com.cep.dto.EnderecoDto;
import br.com.cep.dto.NovoEnderecoDto;
import br.com.cep.mapper.EnderecoMapper;
import br.com.cep.service.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Api(tags="Endereço", value = "Cadastro e dados de endereço por cep")
@RestController
@AllArgsConstructor
@RequestMapping("/endereco")
@Validated
public class EnderecoController {

    private final EnderecoMapper mapper;
    private final EnderecoService service;

    @ApiOperation(value = "Faz o cadastro de um novo endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Endereço foi cadastrado"),
            @ApiResponse(code = 400, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid NovoEnderecoDto enderecoDto){
        service.save(mapper.toEndereco(enderecoDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Retorna os dados de um endereço pelo CEP", notes = "O endpoint utiliza a regra de substituir um dígito da direita para a esquerda por zero até que o endereço seja localizado. Caso não seja localizado é retornado uma exceção indicando Cep inválido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço encontrado"),
            @ApiResponse(code = 400, message = "Cep não foi encontrado"),
    })
    @GetMapping("{cep}")
    public ResponseEntity<EnderecoDto> findByCep(@PathVariable(value = "cep") @Pattern(regexp = "[0-9]{8}", message = "Atributo CEP deve ter 8 caracteres numéricos") String cep){
        return ResponseEntity.ok(mapper.toDto(service.findByCep(cep)));
    }

}
