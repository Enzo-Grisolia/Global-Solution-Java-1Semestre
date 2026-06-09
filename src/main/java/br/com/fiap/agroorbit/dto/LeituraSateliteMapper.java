package br.com.fiap.agroorbit.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.agroorbit.model.LeituraSatelite;
import br.com.fiap.agroorbit.model.Propriedade;
import br.com.fiap.agroorbit.service.PropriedadeService;


@Component
public class LeituraSateliteMapper {

    @Autowired
    private PropriedadeService propriedadeService;

    public LeituraSatelite toModel(LeituraSateliteCreateRequest dto) {
        LeituraSatelite leitura = new LeituraSatelite();
        leitura.setData(dto.getData());
        leitura.setNdvi(dto.getNdvi());
        leitura.setTemperaturaSolo(dto.getTemperaturaSolo());
        leitura.setUmidade(dto.getUmidade());
        leitura.setFonteSatelite(dto.getFonteSatelite());
        propriedadeService.findById(dto.getPropriedadeId())
                .ifPresent(leitura::setPropriedade);
        return leitura;
    }

    public LeituraSatelite toModel(Long id, LeituraSateliteUpdateRequest dto) {
        LeituraSatelite leitura = new LeituraSatelite();
        leitura.setId(id);
        leitura.setData(dto.getData());
        leitura.setNdvi(dto.getNdvi());
        leitura.setTemperaturaSolo(dto.getTemperaturaSolo());
        leitura.setUmidade(dto.getUmidade());
        leitura.setFonteSatelite(dto.getFonteSatelite());
        if (dto.getPropriedadeId() != null) {
            propriedadeService.findById(dto.getPropriedadeId())
                    .ifPresent(leitura::setPropriedade);
        }
        return leitura;
    }

    public LeituraSateliteResponse toDto(LeituraSatelite entity) {
        LeituraSateliteResponse dto = new LeituraSateliteResponse();
        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setNdvi(entity.getNdvi());
        dto.setTemperaturaSolo(entity.getTemperaturaSolo());
        dto.setUmidade(entity.getUmidade());
        dto.setFonteSatelite(entity.getFonteSatelite());
        Propriedade propriedade = entity.getPropriedade();
        if (propriedade != null) {
            dto.setPropriedadeId(propriedade.getId());
            dto.setPropriedadeNome(propriedade.getNome());
        }
        return dto;
    }
}
