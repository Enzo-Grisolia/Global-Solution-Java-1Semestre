package br.com.fiap.agroorbit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.agroorbit.dto.ProdutorCreateRequest;
import br.com.fiap.agroorbit.dto.ProdutorMapper;
import br.com.fiap.agroorbit.dto.ProdutorResponse;
import br.com.fiap.agroorbit.dto.ProdutorUpdateRequest;
import br.com.fiap.agroorbit.model.Produtor;
import br.com.fiap.agroorbit.service.ProdutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/${api.version}/produtores")
@Tag(name = "Produtores", description = "Cadastro de produtores rurais")
public class ProdutorController {

    @Autowired
    private ProdutorService service;

    @Autowired
    private ProdutorMapper mapper;

    @PostMapping
    @Operation(summary = "Cria um novo produtor")
    public ResponseEntity<ProdutorResponse> create(
            @Valid @RequestBody ProdutorCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.toDto(
                        service.createOrUpdate(
                                mapper.toModel(dtoRequest))));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um produtor por id")
    public ResponseEntity<ProdutorResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtores")
    public ResponseEntity<List<ProdutorResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(mapper::toDto)
                        .toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produtor existente")
    public ResponseEntity<ProdutorResponse> update(
            @PathVariable Long id,
            @RequestBody ProdutorUpdateRequest dtoRequest) {
        if (service.findById(id).isPresent()) {
            Produtor produtor = mapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(mapper.toDto(service.createOrUpdate(produtor)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um produtor por id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
