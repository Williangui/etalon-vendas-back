package br.com.etalon.core.repository.jpa;

import br.com.etalon.core.model.Movimentacao;
import br.com.etalon.core.model.dto.MovimentacaoListagemDTO;
import br.com.etalon.core.model.enums.TipoMovimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query("SELECT mov.id as id, mov.produto.nome as produto, mov.data as data, mov.tipo as tipo FROM Movimentacao mov " +
            "WHERE (:id IS NULL OR :id = mov.id) " +
            "AND (:idProduto IS NULL OR :idProduto = mov.produto.id) " +
            "AND (:tipo IS NULL OR :tipo = mov.tipo) " +
            "AND (mov.data BETWEEN :dataInicial AND :dataFinal) " +
            "AND (:idUsuario IS NULL OR :idUsuario = mov.usuario.id)")
    Page<MovimentacaoListagemDTO> listar(Long id, Long idProduto, TipoMovimentacao tipo, LocalDateTime dataInicial,
                                         LocalDateTime dataFinal, Long idUsuario, Pageable pageable);
}
