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

import br.com.fiap.agroorbit.dto.AlertaClimaticoCreateRequest;
import br.com.fiap.agroorbit.dto.AlertaClimaticoMapper;
import br.com.fiap.agroorbit.dto.AlertaClimaticoResponse;
import br.com.fiap.agroorbit.dto.AlertaClimaticoUpdateRequest;
import br.com.fiap.agroorbit.model.AlertaClimatico;
import br.com.fiap.agroorbit.service.AlertaClimaticoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/${api.version}/alertas")
@Tag(name = "Alertas Climáticos", description = "Alertas gerados por propriedade")
public class AlertaClimaticoController {

    @Autowired
    private AlertaClimaticoService service;

    @Autowired
    private AlertaClimaticoMapper mapper;

    @PostMapping
    @Operation(summary = "Cria um novo alerta climático")
    public ResponseEntity<AlertaClimaticoResponse> create(
            @Valid @RequestBody AlertaClimaticoCreateRequest dtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                mapper.toDto(
                        service.createOrUpdate(
                                mapper.toModel(dtoRequest))));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um alerta por id")
    public ResponseEntity<AlertaClimaticoResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Lista todos os alertas")
    public ResponseEntity<List<AlertaClimaticoResponse>> findAll() {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .map(mapper::toDto)
                        .toList());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um alerta existente")
    public ResponseEntity<AlertaClimaticoResponse> update(
            @PathVariable Long id,
            @RequestBody AlertaClimaticoUpdateRequest dtoRequest) {
        if (service.findById(id).isPresent()) {
            AlertaClimatico alerta = mapper.toModel(id, dtoRequest);
            return ResponseEntity.ok(mapper.toDto(service.createOrUpdate(alerta)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um alerta por id")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
