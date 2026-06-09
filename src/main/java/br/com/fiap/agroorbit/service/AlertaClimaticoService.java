package br.com.fiap.agroorbit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.agroorbit.model.AlertaClimatico;
import br.com.fiap.agroorbit.repository.AlertaClimaticoRepository;

/**
 * Camada de serviço da entidade AlertaClimatico.
 *
 * Concentra a lógica de negócio e isola o controller do repositório,
 * seguindo o mesmo padrão Controller-Service-Repository do projeto-base
 * do professor.
 */
@Service
public class AlertaClimaticoService {

    @Autowired
    private AlertaClimaticoRepository repository;

    public AlertaClimatico createOrUpdate(AlertaClimatico entity) {
        return repository.save(entity);
    }

    public Optional<AlertaClimatico> findById(Long id) {
        return repository.findById(id);
    }

    public List<AlertaClimatico> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
