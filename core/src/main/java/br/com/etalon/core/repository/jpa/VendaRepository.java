package br.com.etalon.core.repository.jpa;

import br.com.etalon.core.model.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT venda FROM Venda venda " +
            "INNER JOIN venda.itens item " +
            "WHERE (venda.data BETWEEN :dataInicio AND :dataFim) " +
            "AND (:id IS NULL OR venda.id = :id) " +
            "AND (:valorInicial IS NULL OR venda.valorTotal >= :valorInicial) " +
            "AND (:valorFinal IS NULL OR venda.valorTotal <= :valorFinal) " +
            "AND (:idCliente IS NULL OR venda.cliente.id = :idCliente) " +
            "AND (:idProduto IS NULL OR item.produto.id = :idProduto)")
    Page<Venda> listar(Long id,
                       LocalDateTime dataInicio,
                       LocalDateTime dataFim,
                       BigDecimal valorInicial,
                       BigDecimal valorFinal,
                       Long idCliente,
                       Long idProduto,
                       Pageable pageable);
}
