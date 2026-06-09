package br.com.fiap.agroorbit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.agroorbit.model.Propriedade;
import br.com.fiap.agroorbit.repository.PropriedadeRepository;

/**
 * Camada de serviço da entidade Propriedade.
 *
 * Concentra a lógica de negócio e isola o controller do repositório,
 * seguindo o mesmo padrão Controller-Service-Repository do projeto-base
 * do professor.
 */
@Service
public class PropriedadeService {

    @Autowired
    private PropriedadeRepository repository;

    public Propriedade createOrUpdate(Propriedade entity) {
        return repository.save(entity);
    }

    public Optional<Propriedade> findById(Long id) {
        return repository.findById(id);
    }

    public List<Propriedade> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
