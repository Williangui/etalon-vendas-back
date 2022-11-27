package br.com.etalon.core.repository.jpa;

import br.com.etalon.core.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT prod FROM Produto prod " +
            "WHERE (:id IS NULL OR prod.id = :id) " +
            "AND (:nome IS NULL OR prod.nome LIKE :nome) " +
            "AND (:valorInicial IS NULL OR prod.valor >= :valorInicial) " +
            "AND (:valorFinal IS NULL OR prod.valor <= :valorFinal)")
    Page<Produto> listar(Long id, String nome, Double valorInicial, Double valorFinal, Pageable pageable);
}
