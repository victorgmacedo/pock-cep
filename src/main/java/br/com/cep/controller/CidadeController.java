package br.com.cep.controller;

import br.com.cep.dto.CidadeDto;
import br.com.cep.mapper.CidadeMapper;
import br.com.cep.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags="Cidade", value = "Cadastro e listagem de cidades")
@RestController
@AllArgsConstructor
@RequestMapping("/cidade")
public class CidadeController {

    private final CidadeMapper mapper;
    private final CidadeService service;

    @ApiOperation(value = "Faz o cadastro de uma nova cidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cidade foi cadastrada"),
            @ApiResponse(code = 400, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    public ResponseEntity<CidadeDto> save(@RequestBody @Valid CidadeDto cidadeDto){
        service.save(mapper.toCidade(cidadeDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Retorna todas as cidade sem paginação")
    @GetMapping
    public ResponseEntity<List<CidadeDto>> findAll(){
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toDto).collect(Collectors.toList()));
    }

}
