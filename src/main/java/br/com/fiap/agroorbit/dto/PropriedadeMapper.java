package br.com.fiap.agroorbit.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.agroorbit.model.Produtor;
import br.com.fiap.agroorbit.model.Propriedade;
import br.com.fiap.agroorbit.service.ProdutorService;


@Component
public class PropriedadeMapper {

    @Autowired
    private ProdutorService produtorService;

    public Propriedade toModel(PropriedadeCreateRequest dto) {
        Propriedade propriedade = new Propriedade();
        propriedade.setNome(dto.getNome());
        propriedade.setAreaHectares(dto.getAreaHectares());
        propriedade.setCulturaPrincipal(dto.getCulturaPrincipal());
        propriedade.setLatitude(dto.getLatitude());
        propriedade.setLongitude(dto.getLongitude());
        // Resolve o relacionamento: associa o produtor pelo id informado.
        produtorService.findById(dto.getProdutorId())
                .ifPresent(propriedade::setProdutor);
        return propriedade;
    }

    public Propriedade toModel(Long id, PropriedadeUpdateRequest dto) {
        Propriedade propriedade = new Propriedade();
        propriedade.setId(id);
        propriedade.setNome(dto.getNome());
        propriedade.setAreaHectares(dto.getAreaHectares());
        propriedade.setCulturaPrincipal(dto.getCulturaPrincipal());
        propriedade.setLatitude(dto.getLatitude());
        propriedade.setLongitude(dto.getLongitude());
        if (dto.getProdutorId() != null) {
            produtorService.findById(dto.getProdutorId())
                    .ifPresent(propriedade::setProdutor);
        }
        return propriedade;
    }

    public PropriedadeResponse toDto(Propriedade entity) {
        PropriedadeResponse dto = new PropriedadeResponse();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setAreaHectares(entity.getAreaHectares());
        dto.setCulturaPrincipal(entity.getCulturaPrincipal());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        Produtor produtor = entity.getProdutor();
        if (produtor != null) {
            dto.setProdutorId(produtor.getId());
            dto.setProdutorNome(produtor.getNome());
        }
        return dto;
    }
}
