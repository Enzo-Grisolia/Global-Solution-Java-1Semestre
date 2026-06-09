package br.com.fiap.agroorbit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.agroorbit.model.Produtor;
import br.com.fiap.agroorbit.repository.ProdutorRepository;

/**
 * Camada de serviço da entidade Produtor.
 *
 * Concentra a lógica de negócio e isola o controller do repositório,
 * seguindo o mesmo padrão Controller-Service-Repository do projeto-base
 * do professor.
 */
@Service
public class ProdutorService {

    @Autowired
    private ProdutorRepository repository;

    public Produtor createOrUpdate(Produtor entity) {
        return repository.save(entity);
    }

    public Optional<Produtor> findById(Long id) {
        return repository.findById(id);
    }

    public List<Produtor> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
