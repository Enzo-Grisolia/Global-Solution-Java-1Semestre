package br.com.fiap.agroorbit.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.fiap.agroorbit.model.Produtor;


@Component
public class ProdutorMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Produtor toModel(ProdutorCreateRequest dto) {
        return modelMapper.map(dto, Produtor.class);
    }

    public Produtor toModel(Long id, ProdutorUpdateRequest dto) {
        Produtor produtor = modelMapper.map(dto, Produtor.class);
        produtor.setId(id);
        return produtor;
    }

    public ProdutorResponse toDto(Produtor entity) {
        return modelMapper.map(entity, ProdutorResponse.class);
    }
}
