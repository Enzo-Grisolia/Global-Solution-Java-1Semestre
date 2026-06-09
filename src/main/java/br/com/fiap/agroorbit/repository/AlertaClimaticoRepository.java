package br.com.fiap.agroorbit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.agroorbit.model.AlertaClimatico;


@Repository
public interface AlertaClimaticoRepository extends JpaRepository<AlertaClimatico, Long> {

}
