package br.com.fiap.agroorbit.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.agroorbit.model.AlertaClimatico;
import br.com.fiap.agroorbit.model.Propriedade;
import br.com.fiap.agroorbit.service.PropriedadeService;


@Component
public class AlertaClimaticoMapper {

    @Autowired
    private PropriedadeService propriedadeService;

    public AlertaClimatico toModel(AlertaClimaticoCreateRequest dto) {
        AlertaClimatico alerta = new AlertaClimatico();
        alerta.setTipoAlerta(dto.getTipoAlerta());
        alerta.setSeveridade(dto.getSeveridade());
        alerta.setDescricao(dto.getDescricao());
        alerta.setDataGeracao(dto.getDataGeracao());
        if (dto.getStatus() != null) {
            alerta.setStatus(dto.getStatus());
        }
        propriedadeService.findById(dto.getPropriedadeId())
                .ifPresent(alerta::setPropriedade);
        return alerta;
    }

    public AlertaClimatico toModel(Long id, AlertaClimaticoUpdateRequest dto) {
        AlertaClimatico alerta = new AlertaClimatico();
        alerta.setId(id);
        alerta.setTipoAlerta(dto.getTipoAlerta());
        alerta.setSeveridade(dto.getSeveridade());
        alerta.setDescricao(dto.getDescricao());
        alerta.setDataGeracao(dto.getDataGeracao());
        alerta.setStatus(dto.getStatus());
        if (dto.getPropriedadeId() != null) {
            propriedadeService.findById(dto.getPropriedadeId())
                    .ifPresent(alerta::setPropriedade);
        }
        return alerta;
    }

    public AlertaClimaticoResponse toDto(AlertaClimatico entity) {
        AlertaClimaticoResponse dto = new AlertaClimaticoResponse();
        dto.setId(entity.getId());
        dto.setTipoAlerta(entity.getTipoAlerta());
        dto.setSeveridade(entity.getSeveridade());
        dto.setDescricao(entity.getDescricao());
        dto.setDataGeracao(entity.getDataGeracao());
        dto.setStatus(entity.getStatus());
        Propriedade propriedade = entity.getPropriedade();
        if (propriedade != null) {
            dto.setPropriedadeId(propriedade.getId());
            dto.setPropriedadeNome(propriedade.getNome());
        }
        return dto;
    }
}
