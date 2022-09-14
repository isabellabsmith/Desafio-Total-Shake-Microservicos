package br.com.desafio.totalshake.domain.repository;

import br.com.desafio.totalshake.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    Optional<Pagamento> findById(long id);

    void deleteById(long id);
}
