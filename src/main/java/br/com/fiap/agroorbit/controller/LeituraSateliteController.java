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

import br.com.fiap.agroorbit.dto.LeituraSateliteCreateRequest;
import br.com.fiap.agroorbit.dto.LeituraSateliteMapper;
import br.com.fiap.agroorbit.dto.LeituraSateliteResponse;
import br.com.fiap.agroorbit.dto.LeituraSateliteUpdateRequest;
import br.com.fiap.agroorbit.model.LeituraSatelite;
import br.com.fiap.agroorbit.service.LeituraSateliteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/${api.version}/leituras")
@Tag(name = "Leituras de Satélite", description = "Telemetria de NDVI, solo e umidade")
public class LeituraSateliteController {

    @Autowired
    private LeituraSateliteService service;

    @Autowired
    private LeituraSateliteMapper mapper;

    @PostMapping
    @Operation(summary = "Registra uma nova leitura de satélite")
    public ResponseEntity<LeituraSateliteResponse> create(
            @Valid @RequestBody LeituraSateliteCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.toDto(
                        service.createOrUpdate(
                                mapper.toModel(dtoRequest))));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma leitura por id")
    public ResponseEntity<LeituraSateliteResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Lista todas as leituras")
    public ResponseEntity<List<LeituraSateliteResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(mapper::toDto)
                        .toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma leitura existente")
    public ResponseEntity<LeituraSateliteResponse> update(
            @PathVariable Long id,
            @RequestBody LeituraSateliteUpdateRequest dtoRequest) {
        if (service.findById(id).isPresent()) {
            LeituraSatelite leitura = mapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(mapper.toDto(service.createOrUpdate(leitura)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma leitura por id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
