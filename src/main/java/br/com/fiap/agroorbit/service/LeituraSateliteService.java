package br.com.fiap.agroorbit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.agroorbit.model.LeituraSatelite;
import br.com.fiap.agroorbit.repository.LeituraSateliteRepository;

/**
 * Camada de serviço da entidade LeituraSatelite.
 *
 * Concentra a lógica de negócio e isola o controller do repositório,
 * seguindo o mesmo padrão Controller-Service-Repository do projeto-base
 * do professor.
 */
@Service
public class LeituraSateliteService {

    @Autowired
    private LeituraSateliteRepository repository;

    public LeituraSatelite createOrUpdate(LeituraSatelite entity) {
        return repository.save(entity);
    }

    public Optional<LeituraSatelite> findById(Long id) {
        return repository.findById(id);
    }

    public List<LeituraSatelite> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
