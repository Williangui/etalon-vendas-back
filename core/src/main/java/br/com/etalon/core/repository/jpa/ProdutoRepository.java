package br.com.etalon.core.repository.jpa;

import br.com.etalon.core.model.Produto;
import br.com.etalon.core.model.dto.ProdutoListagemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT prod.id as id, prod.nome as nome, prod.valor as valor, prod.categoria.descricao as categoria FROM Produto prod " +
            "WHERE (:id IS NULL OR prod.id = :id) " +
            "AND (:nome IS NULL OR prod.nome LIKE :nome) " +
            "AND (:valorInicial IS NULL OR prod.valor >= :valorInicial) " +
            "AND (:valorFinal IS NULL OR prod.valor <= :valorFinal) " +
            "AND (:idCategoria IS NULL OR prod.categoria.id = :idCategoria)")
    Page<ProdutoListagemDTO> listar(Long id, String nome, Double valorInicial, Double valorFinal, Long idCategoria, Pageable pageable);
}
