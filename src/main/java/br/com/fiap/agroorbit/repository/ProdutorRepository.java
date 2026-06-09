package br.com.fiap.agroorbit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.agroorbit.model.Produtor;


@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

}
