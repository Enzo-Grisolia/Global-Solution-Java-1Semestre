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

import br.com.fiap.agroorbit.dto.PropriedadeCreateRequest;
import br.com.fiap.agroorbit.dto.PropriedadeMapper;
import br.com.fiap.agroorbit.dto.PropriedadeResponse;
import br.com.fiap.agroorbit.dto.PropriedadeUpdateRequest;
import br.com.fiap.agroorbit.model.Propriedade;
import br.com.fiap.agroorbit.service.PropriedadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/${api.version}/propriedades")
@Tag(name = "Propriedades", description = "Fazendas monitoradas por satélite")
public class PropriedadeController {

    @Autowired
    private PropriedadeService service;

    @Autowired
    private PropriedadeMapper mapper;

    @PostMapping
    @Operation(summary = "Cria uma nova propriedade")
    public ResponseEntity<PropriedadeResponse> create(
            @Valid @RequestBody PropriedadeCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.toDto(
                        service.createOrUpdate(
                                mapper.toModel(dtoRequest))));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma propriedade por id")
    public ResponseEntity<PropriedadeResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Lista todas as propriedades")
    public ResponseEntity<List<PropriedadeResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(mapper::toDto)
                        .toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma propriedade existente")
    public ResponseEntity<PropriedadeResponse> update(
            @PathVariable Long id,
            @RequestBody PropriedadeUpdateRequest dtoRequest) {
        if (service.findById(id).isPresent()) {
            Propriedade propriedade = mapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(mapper.toDto(service.createOrUpdate(propriedade)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma propriedade por id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
