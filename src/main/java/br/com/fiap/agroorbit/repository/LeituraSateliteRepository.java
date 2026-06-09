package br.com.fiap.agroorbit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.agroorbit.model.LeituraSatelite;


@Repository
public interface LeituraSateliteRepository extends JpaRepository<LeituraSatelite, Long> {

}
